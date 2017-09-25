package com.LuDik.EvoAI;

import java.awt.Color;

public class Brain {
	private int brainHeight, brainWidth;
	private Neuron[][] neurons;
	private Creature creature;
	private double[] inputs, hiddenOutputs, hiddenInputs, outputs;
	private float[] rgbColor;

	/*
	 * inputs outputs 0 tot 3: RGB waarden van het linkeroog. 0: verandering in
	 * snelheid. 3 tot 6: RGB waarden van het rechteroog. 1: verandering in
	 * richting. 6: snelheid van creature. 2: hoeveel en of de creature eet. 7:
	 * richting van creature. 3: rood waarde van lichaamskleur. 8: leeftijd van
	 * creature. 4: groen waarde van lichaamskleur. 9: vetvooraad van creature. 5:
	 * blauw waarde van lichaamskleur. 10: constante waarde 1. 6 tot 10: te bepalen
	 * outputs. 11>: geheugencellen. 11>: geheugencellen
	 */

	Brain(int brainHeight, int brainWidth, Creature creature) {
		this.brainHeight = brainHeight;
		this.brainWidth = brainWidth;
		neurons = new Neuron[brainWidth][brainHeight];
		this.creature = creature;
		outputs = new double[brainHeight];
		inputs = new double[brainHeight];
		rgbColor = new float[2];
		for (int i = 0; i < this.brainWidth; i++) {
			for (int j = 0; j < this.brainHeight; j++) {
				neurons[i][j] = new Neuron(brainHeight);
			}
		}
	}

	public void generateInputs() {

		Color leftEyeColor = creature.getLeftEyeColor();
		if (leftEyeColor != null) {
			rgbColor = leftEyeColor.getRGBColorComponents(null);
			for (int i = 0; i < rgbColor.length; i++) {
				inputs[i] = rgbColor[i];
			}
		}

		Color rightEyeColor = creature.getRightEyeColor();
		if (rightEyeColor != null) {
			rgbColor = rightEyeColor.getRGBColorComponents(null);
		}
		// Color rightEyeColor = creature.getRightEyeColor();
		// rightEyeColor.getRGBColorComponents(rgbColor);
		for (int i = 0; i < rgbColor.length; i++) {
			inputs[i + 3] = rgbColor[i];
		}

		inputs[6] = creature.getSpeed();
		inputs[7] = creature.getDirection();
		inputs[8] = creature.getAge();
		inputs[9] = creature.getFat();

		// Als alle andere waardes 0 zijn kan het nogsteeds acties uitvoeren door de
		// constante 1
		inputs[10] = 1d;

		for (int i = 11; i < brainHeight; i++) {
			inputs[i] = outputs[i];
		}

	}

	public double[] feedForward() {
		hiddenOutputs = new double[brainHeight];
		hiddenInputs = new double[brainHeight];

		// loop voor eerste layer van neuralnet
		for (int i = 0; i < brainHeight; i++) {
			hiddenOutputs[i] = neurons[0][i].processNeuron(inputs);
		}

		// loop voor hiddenlayers van neuralnet
		for (int i = 1; i < brainWidth; i++) {
			hiddenInputs = hiddenOutputs;
			for (int j = 0; j < brainHeight; j++) {
				hiddenOutputs[i] = neurons[i][j].processNeuron(hiddenInputs);
			}
		}
		outputs = hiddenOutputs;
		return outputs;
	}

}
