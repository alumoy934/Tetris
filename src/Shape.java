public class Shape {

    public enum Tetrominoes {NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredShape};
    private  Tetrominoes pieceShape;
    private  int coords[][];

    private static int[][][] coordsTable = new int [][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };

    public Shape(){
        coords = new int[4][2];
        setShape(Tetrominoes.NoShape);
    }

    private void setShape(Tetrominoes shapeType) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                coords[i][j] = coordsTable[shapeType.ordinal()][i][j];
            }
        }
        pieceShape = shapeType;
    }

    private void setX (int index, int x){
        coords[index][0] = x;
    }

    private void setY (int index, int y){
        coords[index][1] = y;
    }

    public int getX (int index){
        return coords[index][0];
    }

    public int getY (int index){
        return coords[index][1];
    }

    public Tetrominoes getShape(){
        return pieceShape;
    }

    public void setRandonShape(){
        int number = (int) (Math.random()*7+1);
        Tetrominoes[] values =	Tetrominoes.values();
        setShape(values[number]);
    }

    public int minX()
    {
        int m = coords[0][0];
        for (int i=0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    public int minY(){
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }

    public int maxX(){
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.max(m,coords[i][0]);
        }
        return m;
    }
    public int maxY(){
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.max(m, coords[i][1]);
        }
        return m;
    }

    public Shape rotateLeft(){
        if (pieceShape == Tetrominoes.SquareShape){
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, -coords[i][1]);
            result.setY(i, coords[i][0]);
        }

        return result;
    }

    public Shape rotateRight(){
        if (pieceShape == Tetrominoes.SquareShape){
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, coords[i][1]);
            result.setY(i, -coords[i][0]);
        }

        return result;
    }


}
