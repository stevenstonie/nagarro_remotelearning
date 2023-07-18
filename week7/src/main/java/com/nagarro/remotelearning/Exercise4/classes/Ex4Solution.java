package com.nagarro.remotelearning.Exercise4.classes;

import java.util.random.RandomGenerator;

public class Ex4Solution {
	private static final String[] ARTICLES = { "the", "a", "one", "some", "any" };
	private static final String[] NOUNS = { "boy", "girl", "dog", "town", "car" };
	private static final String[] VERBS = { "drove", "jumped", "ran", "walked", "skipped" };
	private static final String[] PREPOSITIONS = { "to", "from", "over", "under", "on" };
	private static final String[] LINKING_SENTENCES = { "up until then", "after that", "afterwards",
			"time goes by and", "some time after", "after a while", "after a few days", "after a few weeks",
			"next", "then" };
	private static final int SENTENCES_COUNT = 20;
	private static final int LINKING_SENTENCES_INTERVAL = 5;

	private static final char SPACE = ' ';
	private static final char DOT = '.';

	public static void printRandomGeneratedSentences() {
		RandomGenerator randomGenerator = RandomGenerator.getDefault();

		for (int i = 0; i < SENTENCES_COUNT; i++) {
			String sentence = generateRandomSentence(randomGenerator);

			System.out.println(sentence);

			if (i % LINKING_SENTENCES_INTERVAL == 0) {
				System.out.println(getRandomLinkingSentence(randomGenerator));
			}
		}
	}

	public static String[] getLinkingSentences() {
		return LINKING_SENTENCES;
	}

	public static String[] getArticles() {
		return ARTICLES;
	}

	public static String[] getNouns() {
		return NOUNS;
	}

	public static String[] getVerbs() {
		return VERBS;
	}

	public static String[] getPreposition() {
		return PREPOSITIONS;
	}

	private static String generateRandomSentence(RandomGenerator randomGenerator) {
		String article = ARTICLES[randomGenerator.nextInt(ARTICLES.length)];
		String noun = NOUNS[randomGenerator.nextInt(NOUNS.length)];
		String verb = VERBS[randomGenerator.nextInt(VERBS.length)];
		String preposition = PREPOSITIONS[randomGenerator.nextInt(PREPOSITIONS.length)];
		String objectArticle = ARTICLES[randomGenerator.nextInt(ARTICLES.length)];
		String objectNoun = NOUNS[randomGenerator.nextInt(NOUNS.length)];

		String sentence = article.substring(0, 1).toUpperCase() + article.substring(1) + SPACE;
		sentence += noun + SPACE;
		sentence += verb + SPACE;
		sentence += preposition + SPACE;
		sentence += objectArticle + SPACE;
		sentence += objectNoun + DOT + SPACE;

		return sentence;
	}

	private static String getRandomLinkingSentence(RandomGenerator randomGenerator) {
		return LINKING_SENTENCES[randomGenerator.nextInt(LINKING_SENTENCES.length)];
	}
}
