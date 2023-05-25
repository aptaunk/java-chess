package personal.javachess.data;

import personal.javachess.enums.Color;
import personal.javachess.enums.Piece;

public class Move {
    private Color player;
    private int fromRank = -1;
    private int fromFile = -1;
    private int toRank = -1;
    private int toFile = -1;
    private Piece movePiece;
    private int takeRank = -1;
    private int takeFile = -1;
    private Piece takePiece;
    private boolean enPassant;
    private int castleFromRank = -1;
    private int castleFromFile = -1;
    private int castleToRank = -1;
    private int castleToFile = -1;
    private Piece promote;

    public Color getPlayer() {
        return player;
    }
    public void setPlayer(Color player) {
        this.player = player;
    }
    public int getFromRank() {
        return fromRank;
    }
    public void setFromRank(int fromRank) {
        this.fromRank = fromRank;
    }
    public int getFromFile() {
        return fromFile;
    }
    public void setFromFile(int fromFile) {
        this.fromFile = fromFile;
    }
    public int getToRank() {
        return toRank;
    }
    public void setToRank(int toRank) {
        this.toRank = toRank;
    }
    public int getToFile() {
        return toFile;
    }
    public void setToFile(int toFile) {
        this.toFile = toFile;
    }
    public Piece getMovePiece() {
        return movePiece;
    }
    public void setMovePiece(Piece movePiece) {
        this.movePiece = movePiece;
    }
    public int getTakeRank() {
        return takeRank;
    }
    public void setTakeRank(int takeRank) {
        this.takeRank = takeRank;
    }
    public int getTakeFile() {
        return takeFile;
    }
    public void setTakeFile(int takeFile) {
        this.takeFile = takeFile;
    }
    public Piece getTakePiece() {
        return takePiece;
    }
    public void setTakePiece(Piece takePiece) {
        this.takePiece = takePiece;
    }
    public boolean isEnPassant() {
        return enPassant;
    }
    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }
    public int getCastleFromRank() {
        return castleFromRank;
    }
    public void setCastleFromRank(int castleRank) {
        this.castleFromRank = castleRank;
    }
    public int getCastleFromFile() {
        return castleFromFile;
    }
    public void setCastleFromFile(int castleFile) {
        this.castleFromFile = castleFile;
    }
    public int getCastleToRank() {
        return castleToRank;
    }
    public void setCastleToRank(int castleToRank) {
        this.castleToRank = castleToRank;
    }
    public int getCastleToFile() {
        return castleToFile;
    }
    public void setCastleToFile(int castleToFile) {
        this.castleToFile = castleToFile;
    }
    public Piece getPromote() {
        return promote;
    }
    public void setPromote(Piece promote) {
        this.promote = promote;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + fromRank;
        result = prime * result + fromFile;
        result = prime * result + toRank;
        result = prime * result + toFile;
        result = prime * result + ((movePiece == null) ? 0 : movePiece.hashCode());
        result = prime * result + takeRank;
        result = prime * result + takeFile;
        result = prime * result + ((takePiece == null) ? 0 : takePiece.hashCode());
        result = prime * result + (enPassant ? 1231 : 1237);
        result = prime * result + castleFromRank;
        result = prime * result + castleFromFile;
        result = prime * result + castleToRank;
        result = prime * result + castleToFile;
        result = prime * result + ((promote == null) ? 0 : promote.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;
        if (player != other.player)
            return false;
        if (fromRank != other.fromRank)
            return false;
        if (fromFile != other.fromFile)
            return false;
        if (toRank != other.toRank)
            return false;
        if (toFile != other.toFile)
            return false;
        if (movePiece != other.movePiece)
            return false;
        if (takeRank != other.takeRank)
            return false;
        if (takeFile != other.takeFile)
            return false;
        if (takePiece != other.takePiece)
            return false;
        if (enPassant != other.enPassant)
            return false;
        if (castleFromRank != other.castleFromRank)
            return false;
        if (castleFromFile != other.castleFromFile)
            return false;
        if (castleToRank != other.castleToRank)
            return false;
        if (castleToFile != other.castleToFile)
            return false;
        if (promote != other.promote)
            return false;
        return true;
    }

}
