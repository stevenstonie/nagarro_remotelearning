package com.nagarro.remotelearning;

import java.util.concurrent.CountDownLatch;

import com.nagarro.remotelearning.classes.ThreadRaceContext;
import com.nagarro.remotelearning.classes.Utils;

public class ThreadRace {
    public static void main(String... args) {
        ThreadRaceContext context = new ThreadRaceContext();
        Thread[] competitors = new Thread[10];
        CountDownLatch startSignal = new CountDownLatch(1);

        Utils.setCompetitors(competitors, context, startSignal);

        Utils.startCountdown(startSignal);

        Utils.joinCompetitors(competitors); // should i place this in startCountdown()?

        context.listFinalRankings(competitors);
    }
}