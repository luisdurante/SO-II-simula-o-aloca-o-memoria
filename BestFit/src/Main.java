import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// ALOCADOR
// SEM MEM  = MEDE;
// qt processos na mem memtotal/espa�o ocupado
// mostrar quando o alocador n�o consegue aloa
// 32

class Main {
  public static void main(String[] args) {
	RegistradorLogger logger = new RegistradorLogger();
	Processo[] processos = new Processo[200];
	int[] memoria = new int[1024];
	geraProcessos(processos);
	veP(processos);
    preencheMemoria(memoria);
    Alocador a = new Alocador(processos, memoria, logger); 
    Monitor m = new Monitor(processos, memoria, logger);
    a.start();
    m.start();
    
    try {
       
    	a.join();
    	m.join();
        System.out.println("******************************************");
        System.out.println("*                  FIM                   *");
        System.out.println("******************************************");
        logger.gerarLogFinal();
    
    } catch (InterruptedException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  public static void geraProcessos(Processo[] processos) {
	  Random rand = new Random();
	  for (int i = 0; i < processos.length; i++) {
		  processos[i] = new Processo(Integer.toString(i), rand.nextInt(200 - 120 + 1) + 120, rand.nextInt(35 + 20 + 1) + 20);
	  }
  }

  public static void preencheMemoria(int[] memoria){
    for(int i = 0; i < memoria.length ; i++) {
      memoria[i] = -1;
    }
  }
  
  public static void veP(Processo[] p){
	    for(int i = 0; i < p.length ; i++) {
	    	System.out.println("Processo " + i + " Tam " + p[i].getQtUnidadesDeTempo());
	    }
	  }

  public static void printMem(int[] memoria){
    for (int i = 0; i < memoria.length ; i++) {
      System.out.println(memoria[i]);
    }
  }
 
}