class Teste {

	static void teste (){
		ColorImage img=new ColorImage("C:/Users/perei/Google Drive/workspace/ISCTeGRAM/src/Projeto IP 2017-18 ISCTeGRAM 1.jpg");
		//ColorImage img1= new ColorImage("C:/Users/perei/Google Drive/workspace/ISCTeGRAM/src/Projeto IP 2017-18 ISCTeGRAM.jpg");
		//EditordeImagens m1= new EditordeImagens(img1);
		//ColorImage a= img.FilmFrame(45);
		EditordeImagens m2= new EditordeImagens(img);
		m2.Noise(30);
		m2.Film(45);
		//m1.Blur(3);
		m2.Sepia();
		//m2.Vignette(110);
		//m1.Contrast(70);
		//m1.Noise(0);

	}

	static void teste2(){
		ColorImage img=new ColorImage("C:/Users/perei/Google Drive/workspace/ISCTeGRAM/src/Projeto IP 2017-18 ISCTeGRAM 1.jpg");
		EditordeImagens m2= new EditordeImagens(img);
		m2.Noise(30);
		m2.undo();
		m2.undo();
		m2.redo();

		m2.Contrast(30);
		m2.Film(50);
		m2.Blur(1);
		m2.undo();
		m2.Sepia();
		m2.redo();
		m2.undo();
		m2.undo();
		m2.redo();
		m2.redo();
		m2.redo();
	}

	static void teste3(){
		ColorImage img=new ColorImage("C:/Users/perei/Google Drive/workspace/ISCTeGRAM/src/Projeto IP 2017-18 ISCTeGRAM 1.jpg");
		EditordeImagens m= new EditordeImagens(img);
		EfeitosCompostos oldPhoto =new EfeitosCompostos();
		oldPhoto.add(EfeitosCompostos.NOISE,5);
		oldPhoto.add(EfeitosCompostos.SEPIA);
		oldPhoto.add(EfeitosCompostos.VIGNETTE,80);
		m.applyCompositiveEffect(oldPhoto);
		m.undo();
		m.redo();
		m.Film(45);
		m.undo();
	}

}