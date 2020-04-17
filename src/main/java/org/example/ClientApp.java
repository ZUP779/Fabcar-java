/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

public class ClientApp {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "false");
	}
//
//	public static void main(String[] args) throws Exception {
//		CertificationServiceImpl certificationService = new CertificationServiceImpl();
////		certificationService.queryCertificationsByRange("0","999");
//		certificationService.getCertificationByKey("1");
//		certificationService.getCertificationByKey("0");
//	}

	public static void main(String[] args) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get( "connection-org1.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("certification");

			byte[] result;

			result = contract.evaluateTransaction("queryCertificationsByRange","0","999");
			System.out.println(new String(result));

			// contract.submitTransaction("createCar", "CAR10", "VW", "Polo", "Grey", "Mary");

			// result = contract.evaluateTransaction("queryCertification", "0");
			// System.out.println(new String(result));

//			contract.submitTransaction("createCertification", "3", "{\"bizId\":\"3\",\"bizName\":\"test3\",\"createdTime\":1586923325030,\"description\":\"a test certification\",\"filesHash\":\"testHash\",\"filesId\":\"2\",\"tag\":2,\"userId\":\"2\"}");
//
//
//			contract.submitTransaction("createCertification", "5", "{\"bizId\":\"5\",\"bizName\":\"test2\",\"createdTime\":1586923325030,\"description\":\"a test certification\",\"filesHash\":\"testHash\",\"filesId\":\"2\",\"tag\":2,\"userId\":\"2\"}");
			result = contract.submitTransaction("createCertification", "6", "{\"bizId\":\"6\",\"bizName\":\"test3\",\"createdTime\":1586923325030,\"description\":\"a test certification\",\"filesHash\":\"testHash\",\"filesId\":\"2\",\"tag\":2,\"userId\":\"2\"}");
			result = contract.evaluateTransaction("queryCertificationsByRange","0","999");
			System.out.println(new String(result));

//			contract.submitTransaction("deleteCertification","2");

//			result = contract.evaluateTransaction("queryCertificationsByRange","0","999");
//			System.out.println(new String(result));
		}
	}

}
