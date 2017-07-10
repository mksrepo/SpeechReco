package com.example;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Microphone;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class Main {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

		Microphone micro = new Microphone(44100, 16, true, false);

		micro.startRecording();

		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);

		// Simple recognition with generic model
		recognizer.startRecognition(micro.getStream());
		SpeechResult result;
		while ((result = recognizer.getResult()) != null) {

			System.out.format("Hypothesis: %s\n", result.getHypothesis());

		}
		recognizer.stopRecognition();
		micro.stopRecording();
	}
}