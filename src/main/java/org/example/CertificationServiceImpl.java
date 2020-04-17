package org.example;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Author: ZUP779
 * Date:   2020/04/16 0:02
 * Description:
 */
public class CertificationServiceImpl {
    private static Genson genson = new Genson();
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }
    //evaluateTransaction 不会将对assets的修改提交到账本
    private String evaluateTransactionTemplate(String function,String... args) throws Exception{
        Wallet wallet = Wallet.createFileSystemWallet(Paths.get("wallet"));
        byte[] result = null;

        // load a CCP
        Path networkConfigPath = Paths.get("connection-org1.json");
        System.out.println(networkConfigPath);

        Gateway.Builder builder = Gateway.createBuilder();
        System.out.println("h1");
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        System.out.println("here");

        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("certification");

            result = contract.evaluateTransaction(function, args);

        }
        if( result == null)
            return null;

        return new String(result);
    }

    //submitTransaction 会将对assets的修改提交到账本
    private String submitTransactionTemplate(String function,String... args) throws Exception{

        Wallet wallet = Wallet.createFileSystemWallet(Paths.get("wallet"));
        byte[] result = null;

        // load a CCP
        Path networkConfigPath = Paths.get("connection-org1.json");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);


        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("certification");

            result = contract.submitTransaction(function, args);
        }
        if( result == null)
            return null;
        return new String(result);
    }

    public Certification getCertificationByKey(String bizId) throws Exception {
       String result = evaluateTransactionTemplate("queryCertification",bizId);
       Certification certification = genson.deserialize(result, Certification.class);
       return certification;
    }

    public Certification addCertification(Certification certification) throws Exception {
        String certificationState = genson.serialize(certification);
        String result = submitTransactionTemplate("queryCertification",certificationState);

        Certification returnCertification = genson.deserialize(result, Certification.class);
        return returnCertification;
    }

    public Certification updateCertificationByKey(String bizId, Certification certification) throws Exception {
        String certificationState = genson.serialize(certification);
        String result = submitTransactionTemplate("updateCertification", bizId, certificationState);

        Certification returnCertification = genson.deserialize(result, Certification.class);
        return returnCertification;
    }

    public Certification deleteCertificationByKey(String bizId) throws Exception{
        String result = submitTransactionTemplate("deleteCertification",bizId);
        Certification certification = genson.deserialize(result, Certification.class);
        return certification;
    }

    public List<Certification> queryCertificationsByRange(String startBizdId, String endBizdId) throws Exception{
        String result = evaluateTransactionTemplate("queryCertificationsByRange", startBizdId, endBizdId);
        System.out.println(result);
        return null;
    }
}
