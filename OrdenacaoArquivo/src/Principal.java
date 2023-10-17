import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Principal {

    public void geraTabela() throws IOException{

        FileWriter arq = iniciaTabela();

        // Gera arquivos

        Arquivo arqOrd = new Arquivo("arquivoOrdenado.dat");
        Arquivo arqRand = new Arquivo("arquivoRandomico.dat");
        Arquivo arqRev = new Arquivo("arquivoReverso.dat");
        Arquivo auxRand = new Arquivo("arquivoRandomicoAux.dat");
        Arquivo auxRev = new Arquivo("arquivoReversoAux.dat");


        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();



        insertionSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        bubbleSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        selectionSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        binaryInsertionSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        shakeSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        shellSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        combSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        gnomeSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        quickSortPivo(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        mergeSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        quickSortSemPivo(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        coutingSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        radixSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        heapSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        bucketSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        timSort(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);

        mergeSort2(arqOrd,arqRev,arqRand,arq,auxRand,auxRev);


        arq.close();
        exibirTabela();
    }




    // Calcula comparações

    private double calculaCompInsDirOrd(int TL){
        return TL-1;
    }

    private double calculaCompInsDirRev(int TL){
        return ((Math.pow(TL, 2)) + (TL-2)) / 4;
    }

    private double calculaCompInsDirRand(int TL){
        return ((Math.pow(TL, 2)) + (TL-4)) / 4;
    }

    // Calcula movimentações

    private int calculaMovInsDirOrd(int TL){
        return 3 * (TL-1);
    }

    private double calculaMovInsDirRev(int TL){
        return ((Math.pow(TL, 2)) + (9 * TL) - 10) / 4;
    }

    private double calculaMovInsDirRand(int TL){
        return ((Math.pow(TL, 2)) + (3 * TL) - 4) / 2;
    }

    private FileWriter iniciaTabela() throws IOException{
        FileWriter arq = new FileWriter("Tabela.txt");

        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("__________________________________________________________________________________________________________________________________________________________________________________________________________________________%n");
        gravarArq.printf("|Métodos Ordenação | \t\t\tArquivo Ordenado\t\t\t | \t\t\tArquivo em Ordem Reversa\t\t\t | \t\t\tArquivo Randômico\t\t\t |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");
        gravarArq.printf("\t\t | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo | Comp. Prog. *| Comp. Equa. #| Mov. Prog. +| Mov. Equa. -| Tempo |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");

        return arq;
    }

    private void gravaLinhaTabela(FileWriter arq, String metodo, int compOrd, int compRev, int compRand, int movOrd, int movRev, int movRand, double compEquaOrd, double compEquaRev,
                                  double compEquaRand, double movEquaOrd, double movEquaRev, double movEquaRand,int ttotalOrd, int ttotalRev, int ttotalRand) throws IOException{
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("" + metodo + "    |\t" + String.format("%-6.6s", compOrd) + "\t  |\t" + String.format("%-6.6s", compEquaOrd) + "\t |      " + String.format("%-6.6s", movOrd) + " |      " + String.format("%-2.6s", movEquaOrd) + " |\t" + String.format("%-2.6s", ttotalOrd) + "   |\t    " + String.format("%-6.6s", compRev) + "  |\t  " + String.format("%-6.6s", compEquaRev) + "   |\t " + String.format("%-6.6s", movRev) + "  | \t" + String.format("%-6.6s", movEquaRev) + " |  " + String.format("%-2.6s", ttotalRev) + "   |     " + String.format("%-6.6s", compRand) + "   |\t  " + String.format("%-6.6s", compEquaRand) + "     |\t    " + String.format("%-6.6s", movRand) + " |\t " + String.format("%-6.6s", movEquaRand) + "  |  " + String.format("%-4.6s", ttotalRand) + " |%n");
        gravarArq.printf("_________________________________________________________________________________________________________________________________________________________________________________________________________________________|%n");
    }

    private void exibirTabela() throws FileNotFoundException, IOException{

        FileInputStream stream = new FileInputStream("Tabela.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while(linha != null) {
            System.out.println(linha);
            linha = br.readLine();
        }
    }

    public void insertionSort(Arquivo arqOrd,Arquivo arqRev, Arquivo arqRand,  FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Insertion");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.insercaoDireta();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Inserção Direta", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Insertion");
    }

    public void bubbleSort(Arquivo arqOrd,Arquivo arqRev, Arquivo arqRand,  FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Bubble");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.bubbleSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.bubbleSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.bubbleSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Bubble Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Bubble");
    }

    public void selectionSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Selection");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.selectionSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.selectionSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.selectionSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Selection Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Selection");
    }

    public void binaryInsertionSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Binary Insertion");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.binaryInsertionSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.binaryInsertionSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.binaryInsertionSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Binary Insertion Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Binary Insertion");
    }

    public void shakeSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Shake");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.shakeSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.shakeSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.shakeSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Shake Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Shake");
    }

    public void shellSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Shell");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.shellSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.shellSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.shellSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Shell Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Shell");
    }

    public void combSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Comb");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.combSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.combSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.combSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Comb Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Comb");
    }

    public void gnomeSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Gnome");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.gnomeSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.gnomeSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.gnomeSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Gnome Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Gnome");
    }

    public void quickSortPivo(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Quick Pivo");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.quickSortComPivo();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;
        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.quickSortComPivo();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.quickSortComPivo();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Quick Sort Pivo", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Quick Pivo");
    }

    public void mergeSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Merge 1");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.mergeSort1();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.mergeSort1();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.mergeSort1();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Merge Sort 1", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Merge 1");
    }

    public void quickSortSemPivo(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Quick Sem Pivo");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.quickSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

       // arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.quickSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.quickSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Quick Sort Sem Pivo", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Quick Sem Pivo");
    }

    public void coutingSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Couting");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.countingSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.countingSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.countingSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Couting Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Couting");
    }

    public void radixSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Radix");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.radixSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.radixSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.radixSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Radix Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Radix");
    }

    private void heapSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Heap");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.heapSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.heapSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.heapSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Heap Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Heap");
    }
    public void bucketSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Bucket");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.bucketSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.bucketSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.bucketSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Bucket Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Bucket");
    }
    public void timSort(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Tim");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.timSort();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.timSort();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.timSort();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Tim Sort", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Tim");
    }

    public void mergeSort2(Arquivo arqOrd, Arquivo arqRev, Arquivo arqRand, FileWriter arq, Arquivo auxRand, Arquivo auxRev) throws IOException {
        System.out.println("Inicio Merge 2");
        long tini, tfim;
        int compRand = 0, compRev = 0, compOrd = 0, movRand = 0, movRev = 0, movOrd = 0, ttotalRand = 0, ttotalRev = 0, ttotalOrd = 0;
        // Ordenada
        arqOrd.initComp();
        arqOrd.initMov();

        tini = System.currentTimeMillis();
        arqOrd.mergeSort2();
        tfim = System.currentTimeMillis();
        ttotalOrd = (int)(tfim - tini) / 1000;

        //arqOrd.exibir();

        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();

        // Reversa
        arqRev.copiaArquivo(auxRev);
        auxRev.initComp();
        auxRev.initMov();

        tini = System.currentTimeMillis();
        auxRev.mergeSort2();
        tfim = System.currentTimeMillis();
        ttotalRev = (int)(tfim - tini) / 1000;

        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        //auxRev.exibir();

        // Randômica
        arqRand.copiaArquivo(auxRand);
        auxRand.initComp();
        auxRand.initMov();

        tini = System.currentTimeMillis();
        auxRand.mergeSort2();
        tfim = System.currentTimeMillis();
        ttotalRand = (int)(tfim - tini) / 1000;

        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

        //auxRand.exibir();

        gravaLinhaTabela(arq, "Merge Sort 2", compOrd, compRev, compRand, movOrd, movRev, movRand, 0, 0,
                0, 0, 0, 0,
                ttotalOrd, ttotalRev, ttotalRand);
        System.out.println("Fim Merge 2");
    }
}
