package com.kermekx.engine.color;

public class ColorBuilder {

	/**
	 * Couleur noire
	 */
	public static final float[] BLACK = { 0, 0, 0 };
	/**
	 * Couleur rouge
	 */
	public static final float[] RED = { 1, 0, 0 };
	/**
	 * Couleur verte
	 */
	public static final float[] GREEN = { 0, 1, 0 };
	/**
	 * Couleur bleue
	 */
	public static final float[] BLUE = { 0, 0, 1 };
	/**
	 * Couleur blanche
	 */
	public static final float[] WHITE = { 1, 1, 1 };

	/**
	 * Cr�e un vecteur couleur de 3 flotants
	 * 
	 * @param red
	 *            intensit� du rouge de 0 � 1
	 * @param green
	 *            intensit� du vert de 0 � 1
	 * @param blue
	 *            intensit� du bleu de 0 � 1
	 * @return vecteur de la couleur de 3 flotants
	 */
	public static float[] createColor(float red, float green, float blue) {
		return new float[] { red, green, blue };
	}

}
