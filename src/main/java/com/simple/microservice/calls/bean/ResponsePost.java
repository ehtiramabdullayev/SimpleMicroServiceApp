package com.simple.microservice.calls.bean;

public class ResponsePost {
        private final int numberOfPostWereInserted;

        public ResponsePost(int numberOfPostWereInserted) {
            this.numberOfPostWereInserted = numberOfPostWereInserted;
        }

        public int getNumberOfPostWereInserted() {
            return numberOfPostWereInserted;
        }
    }


