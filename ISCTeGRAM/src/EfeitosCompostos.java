public class EfeitosCompostos {

	public static final int NOISE=0;
	public static final int CONTRAST=1;
	public static final int VIGNETTE=2;
	public static final int SEPIA=3;
	public static final int BLUR=4;
	public static final int FILM=5;

	private static final int MAX_OF_EFFECTS=15;
	private int[][] efeitos;
	private int next;



	public EfeitosCompostos (){
		efeitos=new int[2][MAX_OF_EFFECTS];
		next=0;
	}

	public void add (int efeito){
		if(efeito<0 || efeito >5)
			throw new IllegalArgumentException("Efeito Inválido");
		if(next==MAX_OF_EFFECTS)
			throw new IllegalStateException("Número máximos de efeitos aplicados");
		efeitos[0][next]=efeito;
		next++;
	}

	public void add (int efeito, int value){
		if(efeito<0 || efeito >5)
			throw new IllegalArgumentException("Efeito Inválido");
		if(next==MAX_OF_EFFECTS)
			throw new IllegalStateException("Número máximos de efeitos aplicados");
		efeitos[0][next]=efeito;
		efeitos[1][next]=value;
		next++;
	}

	public ColorImage apply(ColorImage img){
		EditordeImagens imagem=new EditordeImagens(img);
		for(int i=0; i<next;i++){
			if(efeitos[0][i]==NOISE)
				imagem.Noise(efeitos[1][i]);
			else if(efeitos[0][i]==CONTRAST)
				imagem.Contrast(efeitos[1][i]);
			else if(efeitos[0][i]==VIGNETTE)
				imagem.Vignette(efeitos[1][i]);
			else if(efeitos[0][i]==SEPIA)
				imagem.Sepia();
			else if(efeitos[0][i]==BLUR)
				imagem.Blur(efeitos[1][i]);
			else if(efeitos[0][i]==FILM)
				imagem.Film(efeitos[1][i]);
		}
		return imagem.getImage();
	}


}