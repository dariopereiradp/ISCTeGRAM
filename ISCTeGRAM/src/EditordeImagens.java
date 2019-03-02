public class EditordeImagens {

	private ColorImage file;

	private static final int INITIAL_SIZE =100;
	private ColorImage[] history;
	private int next;

	public EditordeImagens (ColorImage file){
		this.file=file.copy();
		history= new ColorImage[INITIAL_SIZE];
		next=0;
		history[next]=file;
		next++;
	}

	public ColorImage getImage (){
		return file;
	}

	// Histórico

	private void add (){
		if(next==history.length){
			ColorImage[]v=new ColorImage[2*history.length];
			for(int i=0; i<history.length; i++){
				v[i]=history[i];
			}
			history=v;
		}
		for(int j=next; j<history.length;j++)
			history[j]=null;
		history[next]=file.copy();
		next++;
	}

	public void undo (){
		if(next==1)
			file.paste(0,0,history[0]);
		else{
			next--;
			file.paste(0,0,history[next-1]);
		}
	}

	public void redo(){
		if(history[next]==null)
			file.paste(0,0,history[next-1]);
		else{
			next++;
			file.paste(0,0,history[next-1]);
		}
	}

	//Efeitos

	public void Noise (int intensidade){
		for(int w=0;w<file.getWidth(); w++){
			for(int h=0; h<file.getHeight(); h++){
				if(Math.random()<0.5){
					if(Math.random()<0.5)
						file.setColor(w,h,file.getColor(w,h).brighter(intensidade));
					else
						file.setColor(w,h,file.getColor(w,h).brighter(-intensidade));
				}
			}
		}
		add();
	}

	public void Contrast (int intensidade){
		for(int w=0;w<file.getWidth(); w++){
			for(int h=0; h<file.getHeight(); h++){
				if(file.getColor(w,h).getLuminance()<128)
					file.setColor(w,h,file.getColor(w,h).brighter(-intensidade));
				else
					file.setColor(w,h,file.getColor(w,h).brighter(intensidade));
			}
		}
		add();
	}

	public void Vignette (int distância){
		Point centro= file.centro();
		for(int w=0;w<file.getWidth(); w++){
			for(int h=0; h<file.getHeight(); h++){
				Point p= new Point(w,h);
				if(p.distanceTo(centro)>distância)
					file.setColor(w,h,file.getColor(w,h).brighter((int)(distância-p.distanceTo(centro))));
			}
		}
		add();
	}

	public void Sepia (){
		for(int w=0;w<file.getWidth(); w++){
			for(int h=0; h<file.getHeight(); h++){
				int r=file.getColor(w,h).getR();
				int g=file.getColor(w,h).getG();
				int b=file.getColor(w,h).getB();
				int R=Color.limit((int)(0.4*r+0.77*g+0.2*b));
				int G=Color.limit((int)(0.35*r+0.69*g+0.17*b));
				int B=Color.limit((int)(0.27*r+0.53*g+0.13*b));
				Color c= new Color (R,G,B);
				file.setColor(w,h,c);
			}
		}
		add();
	}

	public void Blur (int r){
		ColorImage img= new ColorImage(file.getWidth(),file.getHeight());
		for(int w=0;w<img.getWidth(); w++){
			for(int h=0; h<img.getHeight(); h++){
				img.setColor(w,h,file.media(r,w,h));
			}
		}
		file.paste(0,0,img);
		add();
	}

	public void Film (int largura){
		file.paste(0,0,file.FilmFrame(largura));
		file.paste(file.getWidth()-largura,0,file.FilmFrame(largura));
		add();
	}

	//Efeitos Compostos
	public void applyCompositiveEffect (EfeitosCompostos effect){
		file.paste(0,0,effect.apply(file));
		add();
	}


}