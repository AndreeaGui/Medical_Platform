package com.assignment3.client;

import com.assignment3.client.grpc.MedicationPlan;
import com.assignment3.client.grpc.PatientMessage;
import com.assignment3.client.grpc.RequestGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {


	public static void main(String[] args) {


//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
//				.usePlaintext()
//				.build();
//
//		RequestGrpc.RequestBlockingStub stub
//				= RequestGrpc.newBlockingStub(channel);
//
//		MedicationPlan medicationPlan = stub.sendMedications(PatientMessage.newBuilder()
//				.setId("0163d1e1-79b5-4b3d-91d3-94a106bfc194")
//				.build());
//
//
//		System.out.println(medicationPlan);
//
//		Controller controller = new Controller(medicationPlan);
		//PillDispenserView view = new PillDispenserView(medicationPlan);
		Controller controller1 = new Controller();

		//SpringApplication.run(ClientApplication.class, args);

		//channel.shutdown();

	}


}
