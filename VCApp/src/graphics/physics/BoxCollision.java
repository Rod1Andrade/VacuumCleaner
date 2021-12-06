package graphics.physics;

/**
 * Caixa de colisao vai definir em uma entidade
 * as posicoes em forma retangular nas quais devem
 * apontar como colisao.
 * <p>
 * Tecnica usada: AABB (Axis Alignment Bounding Box)
 *
 * @author Rodrigo Andrade
 */
public class BoxCollision {

    private int x;
    private int y;
    private int width;
    private int height;

    public BoxCollision(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Verifica se a caixa de colisao esta alinhada com a caixa de colisao atual.
     *
     * @param boxCollision BoxCollision
     * @return TRUE caso tenha colisao e FALSE caso contrario.
     */
    public boolean hasCollision(BoxCollision boxCollision) {
        return boxCollision.x < this.x + this.width &&
                boxCollision.x + boxCollision.width > this.x &&
                boxCollision.y < this.y + this.height &&
                boxCollision.x + boxCollision.height > this.y;
    }

    public void setValues(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
