package com.example.springdemo.grpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class ExampleService extends patientGRPCGrpc.patientGRPCImplBase {

    @Override
    public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
        System.out.println("Inside login");
        String username = request.getUsername();
        String password = request.getPassword();

        APIResponse.Builder response = APIResponse.newBuilder();
        if(username.equals(password)){
            //success
            response.setResponseCode(0).setResponseMessage("Success");

        }else{
            //failure
            response.setResponseCode(100).setResponseMessage("Invalid");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {

    }
}
