/**
 * Represents color images.
 * Image data is represented as a matrix:
 * - the number of lines corresponds to the image height (data.length)
 * - the length of the lines corresponds to the image width (data[*].length)
 * - pixel color is encoded as integers (ARGB)
 */
public class ColorImage {

	private int[][] data; // @colorimage

	ColorImage(String file) {
		this.data = ImageUtil.readColorImage(file);
	}

	public ColorImage(int width, int height) {
		data = new int[height][width];
	}

	public int getWidth() {
		return data[0].length;
	}

	public int getHeight() {
		return data.length;
	}

	public void setColor(int x, int y, Color c) {
		data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
	}

	public Color getColor(int x, int y) {
		int[] rgb = ImageUtil.decodeRgb(data[y][x]);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	public ColorImage copy(){
		ColorImage img=new ColorImage(getWidth(),getHeight());
		for(int w=0;w<getWidth(); w++){
			for(int h=0; h<getHeight(); h++)
				img.setColor(w,h,getColor(w,h));
		}
		return img;
	}

	public Point centro (){
		return new Point (getWidth()/2,getHeight()/2);
	}

	public Color media (int raio,int x,int y){
		int xMin=Math.max(0,x-raio);
		int xMax=Math.min(getWidth(),x+raio);
		int yMin=Math.max(0,y-raio);
		int yMax=Math.min(getHeight(),y+raio);
		int r=0; int g=0; int b=0; int soma=0;

		for(int i=xMin; i<xMax; i++){
			for(int j=yMin; j<yMax; j++){
				r+=getColor(i,j).getR();
				g+=getColor(i,j).getG();
				b+=getColor(i,j).getB();
				soma++;
			}
		}
		return new Color(r/soma,g/soma,b/soma);
	}


	public void paste(int x,int y, ColorImage img){
		for(int w=x,a=0;w<x+this.getWidth()&&a<img.getWidth(); w++,a++){
			for(int h=y,b=0; h<y+this.getHeight()&&b<img.getHeight(); h++,b++)
				setColor(w,h,img.getColor(a,b));
		}
	}

	public void Rectangle(Color c,int x,int y,int width, int height) {
		for (int w = 0; w < width; w++)
			for (int h = 0; h < height; h++)
				setColor(x + w, y + h, c);
	}

	public void Triangle(int x, int y, int width){
		for (int i = 0; i < width; i++) {
			Rectangle(Color.BLACK, x, y + i, width - i, 1);
		}
	}

	public ColorImage FilmFrame (int largura){
		ColorImage img= new ColorImage(largura,getHeight());
		ColorImage white = new ColorImage((int)(0.65*largura),(int)(0.14*getHeight()));
		white.Rectangle(Color.WHITE,0,0,white.getWidth(),white.getHeight());
		white.Triangle(0,0,(int)(0.2*largura));
		img.paste((int)(largura/5.5),(int)(0.07*getHeight()),white);
		img.paste((int)(largura/5.5),(int)(0.31*getHeight()),white);
		img.paste((int)(largura/5.5),(int)(0.55*getHeight()),white);
		img.paste((int)(largura/5.5),(int)(0.79*getHeight()),white);

		return img;
	}		


}