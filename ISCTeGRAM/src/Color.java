/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */
public class Color {

	private final int[] rgb; // @color
	final static Color BLACK=new Color(0,0,0);
	final static Color WHITE=new Color(255,255,255);

	/**
	 * Creates an RGB color. Provided values have to 
	 * be in the interval [0, 255]
	 */
	public Color(int r, int g, int b) {
		if(!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);
		
		this.rgb = new int[] {r, g, b};
	}

	/**
	 * Red value [0, 255]
	 */
	public int getR() {
		return rgb[0];
	}

	/**
	 * Green value [0, 255]
	 */
	public int getG() {
		return rgb[1];
	}

	/**
	 * Blue value [0, 255]
	 */
	public int getB() {
		return rgb[2];
	}

	/**
	 * Obtains the luminance in the interval [0, 255].
	 */
	public int getLuminance() {
		return (int) Math.round(rgb[0]*.21 + rgb[1]*.71 + rgb[2]*.08);
	}

	public Color inverse() {
		return new Color(255-rgb[0],255-rgb[1], 255-rgb[2]); 
	}
	
	
	public static boolean valid(int value) {
		return value >= 0 && value <= 255;
	}
	
	public static int limit (int n){
		if (n<0) return 0;
		if (n>255) return 255;
		return n;
	}
	
	public Color brighter(int f){
		return new Color(limit(getR()+f),limit(getG()+f),limit(getB()+f));
	}

}
