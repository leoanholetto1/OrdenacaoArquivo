import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {
    public final int tf = 1022;
    private int codigo;
    private char lixo[] = new char[tf];

    public Registro() {
        this.codigo = codigo;

        for(int i = 0; i<tf; i++)
            lixo[i] = 'X';
    }

    public Registro(int codigo){

        this.codigo = codigo;

        for(int i = 0; i<tf; i++)
            lixo[i] = 'X';
    }

    public void gravaNoArq(RandomAccessFile arquivo) {

        try {
            arquivo.writeInt(codigo);

            for (int i = 0; i < tf; i++) {
                arquivo.writeChar(lixo[i]);
            }

        } catch (IOException e)
        { }
    }

    public void leDoArq(RandomAccessFile arquivo) {

        try {
            this.codigo = arquivo.readInt();

            for (int i = 0; i < tf; i++)
                this.lixo[i] = arquivo.readChar();

        } catch (IOException e)
        { }
    }

    static int length(){
        return (2048);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void exibirRegistro(){
        System.out.println(codigo);
    }
}
