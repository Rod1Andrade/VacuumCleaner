package graphics.screen;

/**
 * Tela de apresentacao (renderizacao) dos pixels.
 *
 * @author Rodrigo Andrade
 */
public class DisplayScreen {

    private int width;
    private int height;

    // Vetor de inteiros para armazenar os enderecos dos pixels bufferizados da imagem BufferedImage.
    protected int[] pixels;

    public DisplayScreen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }

    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    /**
     * Formacao da renderizacao.
     */
    public void render() {
        // Forma de tratar uma matriz no formato de um vetor.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xff00ff;
            }
        }
    }

    /**
     * Pega pixel de determinada posicao do vetor de pixels.
     *
     * @param index INTEIRO valor inteiro que deve estar dentro dos limites do vetor de pixels.
     * @return INT representando um dado inteiro bufferizado para ser usado pelo BufferedImage.
     */
    public int getPixel(int index) {
        return pixels[index];
    }

}
