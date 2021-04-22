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


}
