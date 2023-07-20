package com.nagarro.remotelearning;

import java.util.concurrent.CountDownLatch;

import com.nagarro.remotelearning.classes.ThreadRaceContext;
import com.nagarro.remotelearning.classes.Utils;

public class ThreadRelayRace {
    public static void main(String[] args) {
        Thread[] teams = new Thread[10];
        ThreadRaceContext context = new ThreadRaceContext();
        CountDownLatch startSignal = new CountDownLatch(1);

        Utils.createTeams(teams, context, startSignal);

        Utils.startRace(startSignal);

        Utils.joinAllTeams(teams); // can i place this in startRace()?

        context.listFinalRankings(teams);
    }
}
