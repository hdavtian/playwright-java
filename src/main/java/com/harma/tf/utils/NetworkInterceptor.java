package com.harma.tf.utils;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NetworkInterceptor implements Consumer<Request> {
    private final List<Request> capturedRequests = new ArrayList<>();

    @Override
    public void accept(Request request) {
        capturedRequests.add(request);
    }

    public List<Request> getCapturedRequests() {
        return capturedRequests;
    }
}
