package com.quancheng.saluki.example.server;

import com.quancheng.examples.model.hello.HelloReply;
import com.quancheng.examples.model.hello.HelloRequest;
import com.quancheng.examples.service.HelloService;
import com.quancheng.saluki.boot.SalukiService;

import io.grpc.stub.StreamObserver;

@SalukiService
public class HelloServiceImpl implements HelloService {

  @Override
  public HelloReply sayHello(HelloRequest request) {
    HelloReply reply = new HelloReply();
    reply.setMessage(request.getName());
    // int registryPort = 0;
    // Preconditions.checkState(registryPort != 0, "RegistryPort can not be null", registryPort);
    return reply;
  }


  @Override
  public void sayHelloStream(HelloRequest hellorequest,
      StreamObserver<HelloReply> responseObserver) {
    try {
      for (int i = 0; i < 10; i++) {
        HelloReply reply = new HelloReply();
        reply.setMessage(hellorequest.getName());
        responseObserver.onNext(reply);
        Thread.sleep(10000);
      }
    } catch (Exception e) {
      responseObserver.onError(e);
    }
    responseObserver.onCompleted();
  }

}
