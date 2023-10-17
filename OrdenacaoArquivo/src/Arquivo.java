import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class Arquivo {

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    public String getNomearquivo() {
        return nomearquivo;
    }

    public void setNomearquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }

    public Arquivo(String nomearquivo) {
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e) {
        }
    }

    public void copiaArquivo(Arquivo aux1){
        Registro reg = new Registro();

        seekArq(0);
        aux1.truncate(0);
        aux1.seekArq(0);
        while(!eof()){
            reg.leDoArq(arquivo);
            reg.gravaNoArq(aux1.arquivo);
        }
    }

    public void truncate(long pos) {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {
        }
    }

    public boolean eof() {
        boolean retorno = false;

        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) {
        }

        return (retorno);
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
        }
    }

    public int fileSize() {
        try {
            return (int) arquivo.length() / Registro.length();
        } catch (IOException e) {
        }

        return 0;
    }

    public void initComp() {
        comp = 0;
    }

    public void initMov() {
        mov = 0;
    }

    public void exibirUmRegistro(int pos) {
        Registro aux = new Registro();

        seekArq(pos);

        System.out.println("Posicao " + pos);

        aux.leDoArq(arquivo);
        aux.exibirRegistro();
    }

    public void exibir() {
        Registro aux = new Registro();

        for(int i=0; i < fileSize();i++){
            seekArq(i);
            aux.leDoArq(arquivo);
            System.out.printf("%d ",aux.getCodigo());
        }
        System.out.println();

    }

    public void geraArquivoOrdenado() {
        truncate(0);

        for (int i = 0; i < 1024; i++) {
            new Registro(i).gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        truncate(0);

        for (int i = 1024; i > 0; i--)
            new Registro(i).gravaNoArq(arquivo);
    }

    public void geraArquivoRandomico() {
        truncate(0);
        for (int i = 0; i < 1024; i++)
            new Registro(new Random().nextInt(713)).gravaNoArq(arquivo);

    }

    public void insercaoDireta() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int j, aux;
        for(int i=1; i<fileSize(); i++) {

            seekArq(i);
            reg2.leDoArq(arquivo);
            seekArq(i-1);
            reg1.leDoArq(arquivo);

            j = i;
            comp++;
            while(j>0 && reg2.getCodigo() < reg1.getCodigo()) {
                mov++;

                seekArq(j);
                reg1.gravaNoArq(arquivo);

                j--;

                seekArq(j-1);
                reg1.leDoArq(arquivo);
                comp++;
            }

            seekArq(j);
            reg2.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void bubbleSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int TL = fileSize();
        while(TL > 1){

            for(int i = 0; i<fileSize()-1; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getCodigo() > reg2.getCodigo()) {
                    mov+=2;

                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                }
            }
            TL--;
        }
    }

    public void shakeSort(){
        int inicio = 0, fim = fileSize() - 1;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        while(inicio < fim){

            for(int i = 0; i < fileSize() - 1; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                comp++;

                if(reg1.getCodigo() > reg2.getCodigo()) {
                    mov+=2;

                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                }
            }

            fim--;

            for(int j=fileSize()-1; j>0; j--){
                seekArq(j-1);
                reg2.leDoArq(arquivo);
                reg1.leDoArq(arquivo);
                comp++;

                if(reg1.getCodigo() < reg2.getCodigo()) {
                    mov+=2;

                    seekArq(j-1);
                    reg1.gravaNoArq(arquivo);
                    seekArq(j);
                    reg2.gravaNoArq(arquivo);
                }
            }

            inicio++;
        }
    }

    public void quickSortComPivo(){
        quickSortComPivo(0, fileSize() - 1);
    }

    public void quickSortComPivo(int ini, int fim){
        int i = ini, j = fim, aux;

        Registro pivo = new Registro();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        seekArq((i+fim)/2);

        pivo.leDoArq(arquivo);
        seekArq(i);
        reg1.leDoArq(arquivo);
        seekArq(j);
        reg2.leDoArq(arquivo);
        while(i<=j){
            comp++;

            while(reg1.getCodigo() < pivo.getCodigo()){
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
                comp++;
            }

            comp++;

            while(reg2.getCodigo() > pivo.getCodigo()) {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                comp++;
            }

            if(i<=j){
                mov+=2;

                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);

                i++;
                j--;

                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
            }
        }

        if(ini < j)
            quickSortComPivo(ini, j);

        if(i < fim)
            quickSortComPivo(i, fim);
    }



    public void fusao(Arquivo arq1, Arquivo arq2, int seq){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int k = 0, aux = seq, i = 0, j = 0;
        while(k < fileSize()){

            while(i < seq && j < seq){

                arq1.seekArq(i);
                reg1.leDoArq(arq1.arquivo);
                arq2.seekArq(j);
                reg2.leDoArq(arq2.arquivo);

                comp++;
                if(reg1.getCodigo() < reg2.getCodigo()) {
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    i++;
                }
                else{
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    j++;
                }

                mov++;
            }

            while(i < seq){
                mov++;

                arq1.seekArq(i++);
                reg1.leDoArq(arq1.arquivo);
                seekArq(k++);
                reg1.gravaNoArq(arquivo);
            }

            while(j < seq){
                mov++;

                arq2.seekArq(j++);
                reg2.leDoArq(arq2.arquivo);
                seekArq(k++);
                reg2.gravaNoArq(arquivo);
            }

            seq+=aux;
        }
    }

    public void merge1(int ini, int l, int r){

        Arquivo arquivo1 = new Arquivo("parte1.dat");
        Arquivo arquivo2 = new Arquivo("parte2.dat");

        int seq = 1;
        while(seq < fileSize()) {
            arquivo1.truncate(0);
            arquivo2.truncate(0);

            particao(arquivo1, arquivo2);
            fusao(arquivo1, arquivo2, seq);

            seq*=2;
        }
    }
    public void mergeSort1(){
        merge1(0, 0, fileSize()-1);
    }

    public void particao(Arquivo arq1, Arquivo arq2){
        Registro reg = new Registro();

        int tam = fileSize()/2;
        for(int i = 0; i<tam; i++){
            mov+=2;

            seekArq(i);
            reg.leDoArq(arquivo);
            arq1.seekArq(i);

            reg.gravaNoArq(arq1.arquivo);
            seekArq(i+tam);

            reg.leDoArq(arquivo);
            arq2.seekArq(i);

            reg.gravaNoArq(arq2.arquivo);
        }
    }


    public int buscaMaior(){
        seekArq(0);
        int maior = -1;

        Registro reg = new Registro();
        while(!eof()){
            reg.leDoArq(arquivo);

            comp++;

            if(maior < reg.getCodigo())
                maior = reg.getCodigo();
        }

        return maior;
    }


    public int getNextGap(int gap){
        gap = (gap*10)/13;

        if(gap < 1)
            return 1;
        return gap;
    }

    public void combSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int TL = fileSize();
        int gap = TL;
        boolean trocou = true;

        while(gap != 1 || trocou){

            gap = getNextGap(gap);
            trocou = false;

            for(int i=0; i<TL-gap; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+gap);
                reg2.leDoArq(arquivo);

                comp++;

                if(reg1.getCodigo() > reg2.getCodigo()){

                    seekArq(i+gap);
                    reg1.gravaNoArq(arquivo);
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    trocou = true;

                    mov+=2;
                }
            }
        }
    }

    public void gnomeSort(){
        int i = 0;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(i < fileSize()){

            if(i == 0)
                i++;

            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(i-1);
            reg2.leDoArq(arquivo);

            comp++;

            if(reg1.getCodigo() >= reg2.getCodigo())
                i++;
            else{
                seekArq(i-1);
                reg1.gravaNoArq(arquivo);
                seekArq(i);
                reg2.gravaNoArq(arquivo);

                i--;
                mov+=2;
            }
        }
    }

    public void selectionSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        for(int i=0; i<fileSize();i++){
            int menor = i;
            for(int j=i+1;j<fileSize();j++){
                seekArq(menor);
                reg1.leDoArq(arquivo);
                seekArq(j);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getCodigo() > reg2.getCodigo()){
                    menor = j;
                }
            }
            if(menor != i){
                mov+=2;
                seekArq(menor);
                reg1.leDoArq(arquivo);
                seekArq(i);
                reg2.leDoArq(arquivo);
                seekArq(i);
                reg1.gravaNoArq(arquivo);
                seekArq(menor);
                reg2.gravaNoArq(arquivo);
            }
        }
    }

    int binarySearch(int item, int low, int high) {
        Registro reg1 = new Registro();
        int mid = (low + high) / 2;
        seekArq(mid);
        reg1.leDoArq(arquivo);
        comp++;
        while (low <= high && item != reg1.getCodigo()) {
            if (item > reg1.getCodigo())
                low = mid + 1;
            else
                high = mid - 1;
            mid = (low + high) / 2;
            seekArq(mid);
            reg1.leDoArq(arquivo);
            comp++;
        }
        if(item > reg1.getCodigo())
            return mid+1;
        return mid;
    }

    public void binaryInsertionSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        for (int i = 1; i < fileSize(); ++i) {
            int j = i - 1;
            seekArq(i);
            reg1.leDoArq(arquivo);
            int valor = reg1.getCodigo();
            int loc = binarySearch(valor, 0, j);
            while (j >= loc) {
                mov++;
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(j+1);
                reg2.gravaNoArq(arquivo);
                j--;
            }
            seekArq(j+1);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void shellSort(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int h=1;
        while(h * 3 + 1 < fileSize()) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < fileSize(); i++) {
                seekArq(i);
                reg2.leDoArq(arquivo);

                int j = i;
                seekArq(j - h);
                reg1.leDoArq(arquivo);
                comp++;
                while (j >= h && reg2.getCodigo() < reg1.getCodigo()) {
                    mov++;
                    seekArq(j);
                    reg1.gravaNoArq(arquivo);

                    j -= h;
                    if(j>=h){
                        seekArq(j-h);
                        reg1.leDoArq(arquivo);
                    }
                    comp++;
                }

                seekArq(j);
                reg2.gravaNoArq(arquivo);
                mov++;
            }
            h = (h-1)/3;
        }
    }

    private int particaoQuick(int ini, int fim){
        int i = ini, j = fim;
        int valor;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(i < j){
            comp++;
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            while(i < j && reg1.getCodigo() <= reg2.getCodigo()){
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
                comp++;
            }
            mov+=2;
            seekArq(j);
            reg1.gravaNoArq(arquivo);
            seekArq(i);
            reg2.gravaNoArq(arquivo);
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
            comp++;
            while(j > i && reg2.getCodigo() >= reg1.getCodigo()){
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                comp++;
            }
            mov+=2;
            seekArq(j);
            reg1.gravaNoArq(arquivo);
            seekArq(i);
            reg2.gravaNoArq(arquivo);
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);
        }
        return i;
    }


    public void ordena(int esq,int dir){
        if(esq<=dir){
            int m = particaoQuick(esq, dir);
            ordena(esq,m-1);
            ordena(m+1,dir);
        }
    }
    public void quickSort(){
        ordena(0, fileSize()-1);
    }

    public void countingSort(){
        int maior=buscaMaior();
        ArrayList<Arquivo> lista = new ArrayList<Arquivo>();
        for(int i = 0; i <=maior ;i++){
            lista.add(new Arquivo("temp"+i+".dat"));
            lista.get(i).truncate(0);
        }
        Registro reg = new Registro();
        Registro reg2 = new Registro();
        for(int j=0; j<fileSize();j++){
            seekArq(j);
            reg.leDoArq(arquivo);
            int pos = reg.getCodigo();
            mov++;
            reg.gravaNoArq(lista.get(pos).getArquivo());
        }
        int tl=0;
        for(int i = 0; i <=maior ;i++){
            for(int j=0; j<lista.get(i).fileSize();j++){
                lista.get(i).seekArq(j);
                reg.leDoArq(lista.get(i).getArquivo());
                seekArq(tl);
                reg.gravaNoArq(arquivo);
                mov++;
                tl++;
            }
        }
    }

    public void countingSortForRadix(int d){
        int maior=buscaMaior();
        ArrayList<Arquivo> lista = new ArrayList<Arquivo>();
        Registro reg = new Registro(0);
        Registro reg2 = new Registro(0);
        Registro reg3;
        Arquivo saida = new Arquivo("novo.dat");
        saida.truncate(0);
        for(int i = 0; i <10 ;i++){
            lista.add(new Arquivo("radix"+i+".dat"));
            lista.get(i).seekArq(0);
            reg.gravaNoArq(lista.get(i).getArquivo());
        }
        for(int j=0; j<fileSize();j++){
            seekArq(j);
            reg.leDoArq(arquivo);
            reg.gravaNoArq(saida.getArquivo());
            int pos = (reg.getCodigo()/d)%10;
            lista.get(pos).seekArq(0);
            reg.leDoArq(lista.get(pos).getArquivo());
            reg.setCodigo(reg.getCodigo()+1);
            lista.get(pos).seekArq(0);
            mov+=2;
            reg.gravaNoArq(lista.get(pos).getArquivo());
        }
        lista.get(0).seekArq(0);
        reg.leDoArq(lista.get(0).getArquivo());
        for(int i = 1; i <10 ;i++){
            lista.get(i).seekArq(0);
            reg2.leDoArq(lista.get(i).getArquivo());
            reg2.setCodigo(reg2.getCodigo()+reg.getCodigo());
            lista.get(i).seekArq(0);;
            mov++;
            reg2.gravaNoArq(lista.get(i).getArquivo());
            lista.get(i).seekArq(0);
            reg.leDoArq(lista.get(i).getArquivo());
        }
        int tl=0;
        for(int j=fileSize()-1; j>=0;j--){
            seekArq(j);
            reg.leDoArq(arquivo);
            mov+=2;
            int pos = (reg.getCodigo()/d)%10;
            lista.get(pos).seekArq(0);
            reg2.leDoArq(lista.get(pos).getArquivo());
            saida.seekArq(reg2.getCodigo()-1);
            reg.gravaNoArq(saida.getArquivo());
            reg2.setCodigo(reg2.getCodigo() - 1);
            lista.get(pos).seekArq(0);
            reg2.gravaNoArq(lista.get(pos).getArquivo());
        }

        for(int j=0; j<fileSize();j++){
            saida.seekArq(j);
            reg.leDoArq(saida.getArquivo());
            mov++;
            seekArq(j);
            reg.gravaNoArq(arquivo);
        }

    }



    public void radixSort(){
        int maior = buscaMaior();
        for(int d=1; maior/d >0 ; d*=10){
            countingSortForRadix(d);
        }
    }


    public void heapSort(){
        int pai, TL = fileSize(), FE, FD, maiorF;

        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        while(TL > 0){

            pai = TL/2-1;

            while(pai >=0){
                FE = pai*2+1;
                FD = FE+1;
                maiorF = FE;

                seekArq(FD);
                reg1.leDoArq(arquivo);
                seekArq(FE);
                reg2.leDoArq(arquivo);

                if(FD < TL && reg1.getCodigo() > reg2.getCodigo())
                    maiorF = FD;

                seekArq(pai);
                reg1.leDoArq(arquivo);
                seekArq(maiorF);
                reg2.leDoArq(arquivo);

                comp+=2;

                if(reg1.getCodigo() < reg2.getCodigo()) {
                    mov+=2;

                    seekArq(pai);
                    reg2.gravaNoArq(arquivo);
                    seekArq(maiorF);
                    reg1.gravaNoArq(arquivo);
                }

                pai--;
            }

            TL--;
            seekArq(0);
            reg1.leDoArq(arquivo);
            seekArq(TL);
            reg2.leDoArq(arquivo);
            seekArq(TL);

            reg1.gravaNoArq(arquivo);
            seekArq(0);
            reg2.gravaNoArq(arquivo);
            mov+=2;
        }
    }

    public void bucketSort(){
        int intervalo = 0,tl=0;
        Registro reg = new Registro();
        ArrayList<Arquivo> arquivos = new ArrayList();
        int maior = buscaMaior();
        while(intervalo <= maior){
            arquivos.add(new Arquivo("balde"+tl+".dat"));
            arquivos.get(tl).truncate(0);
            for(int i=0; i<fileSize(); i++){
                seekArq(i);
                reg.leDoArq(arquivo);
                comp++;
                if(reg.getCodigo()>=intervalo && reg.getCodigo() < intervalo+10){
                    reg.gravaNoArq(arquivos.get(tl).getArquivo());
                    mov++;
                }
            }
            intervalo+=10;
            tl++;
        }
        for(int i=0;i <tl;i++){
            arquivos.get(i).bubbleSort();
        }
        truncate(0);
        seekArq(0);
        for(int i=0;i <tl;i++){
            for(int j=0;j<arquivos.get(i).fileSize();j++){
                mov++;
                arquivos.get(i).seekArq(j);
                reg.leDoArq(arquivos.get(i).getArquivo());
                reg.gravaNoArq(arquivo);
            }
        }
    }

    public void insercaoDiretaTim(int ini, int fim){
        int temp = 0;
        int j = 0;

        Registro reg = new Registro();
        Registro reg2 = new Registro();

        for(int i = ini+1; i <= fim; i++){
            temp = i;
            j = i-1;

            seekArq(j);
            reg.leDoArq(arquivo);
            seekArq(temp);
            reg2.leDoArq(arquivo);

            comp++;

            while(j >= 0 && reg2.getCodigo() <= reg.getCodigo()){
                seekArq(j);
                reg.leDoArq(arquivo);
                seekArq(j+1);
                reg.gravaNoArq(arquivo);

                j--;

                seekArq(j);
                reg.leDoArq(arquivo);

                comp++;
                mov++;
            }

            seekArq(j+1);
            reg2.gravaNoArq(arquivo);

            mov++;
        }
    }

    public void mergeTim(int ini, int meio, int fim){
        int i, j, k;
        int n1 = meio - ini + 1;
        int n2 = fim - meio;

        Registro reg = new Registro();
        Registro reg2 = new Registro();
        Arquivo arq1 = new Arquivo("temp.dat");
        Arquivo arq2 = new Arquivo("temp2.dat");

        for(i = 0; i < n1; i++){
            mov++;

            seekArq(ini+i);
            reg.leDoArq(arquivo);
            arq1.seekArq(i);
            reg.gravaNoArq(arq1.arquivo);
        }

        for(j = 0; j < n2; j++){
            seekArq(meio + 1 + j);
            reg.leDoArq(arquivo);
            arq2.seekArq(j);
            reg.gravaNoArq(arq2.arquivo);

            mov++;
        }

        i = j = 0;
        k = ini;
        while(i < n1 && j < n2){
            arq1.seekArq(i);
            reg.leDoArq(arq1.arquivo);
            arq2.seekArq(j);
            reg2.leDoArq(arq2.arquivo);

            comp++;

            if(reg.getCodigo() <= reg2.getCodigo()){
                seekArq(k);
                reg.gravaNoArq(arquivo);
                i++;
            }
            else{
                seekArq(k);
                reg2.gravaNoArq(arquivo);
                j++;
            }

            k++;
            mov++;
        }

        while(i<n1){
            arq1.seekArq(i);
            reg.leDoArq(arq1.arquivo);
            seekArq(k++);
            reg.gravaNoArq(arquivo);

            i++;
            mov++;
        }

        while(j<n2){
            arq2.seekArq(j);
            reg.leDoArq(arq2.arquivo);
            seekArq(k++);
            reg.gravaNoArq(arquivo);

            j++;
            mov++;
        }
    }

    public void timSort(){
        int divisor = 32;
        for(int i = 0; i < fileSize(); i+=divisor)
            insercaoDiretaTim(i, Math.min(i+divisor-1, (fileSize()-1)));

        for(int i = divisor; i < fileSize(); i*=2){

            for(int ini = 0; ini < fileSize(); ini+=2*i){

                int meio = ini + i - 1;
                int fim = Math.min((ini + 2 * i - 1), (fileSize()-1));

                if(meio < fim)
                    mergeTim(ini, meio, fim);
            }
        }

    }

    public void fusao2(int ini1, int fim1, int ini2, int fim2, Arquivo arquivo1){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int i = ini1, j = ini2, k=0;
        while(i<=fim1 && j<=fim2){
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            comp++;
            if(reg1.getCodigo() < reg2.getCodigo()) {
                arquivo1.seekArq(k++);
                reg1.gravaNoArq(arquivo1.arquivo);

                i++;
            }
            else{
                arquivo1.seekArq(k++);
                reg2.gravaNoArq(arquivo1.arquivo);

                j++;
            }

            mov++;
        }

        while(i<=fim1){
            mov++;

            seekArq(i++);
            reg1.leDoArq(arquivo);
            arquivo1.seekArq(k++);
            reg1.gravaNoArq(arquivo1.arquivo);
        }

        while(j<=fim2){
            mov++;

            seekArq(j++);
            reg2.leDoArq(arquivo);
            arquivo1.seekArq(k++);
            reg2.gravaNoArq(arquivo1.arquivo);
        }

        i=0;
        while(i < k) {
            mov++;

            arquivo1.seekArq(i);
            reg1.leDoArq(arquivo1.arquivo);
            seekArq(i+ini1);
            reg1.gravaNoArq(arquivo);

            i++;
        }
    }

    public void merge2(int esq, int dir, Arquivo arquivo1){

        if(esq<dir){
            int meio = (esq+dir)/2;

            merge2(esq, meio, arquivo1);
            merge2(meio+1, dir, arquivo1);

            fusao2(esq, meio, meio+1, dir, arquivo1);
        }
    }


    public void mergeSort2(){
        Arquivo arquivo1 = new Arquivo("tempMerge.dat");
        copiaArquivo(arquivo1);
        merge2(0, fileSize()-1, arquivo1);
    }
}

