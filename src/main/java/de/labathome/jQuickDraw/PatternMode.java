package de.labathome.jQuickDraw;

public enum PatternMode {

    srcCopy    ( 0),
    srcOr      ( 1),
    srcXor     ( 2),
    srcBic     ( 3),

    notSrcCopy ( 4),
    notSrcOr   ( 5),
    notSrcXor  ( 6),
    notSrcBic  ( 7),

    patCopy    ( 8),
    patOr      ( 9),
    patXor     (10),
    patBic     (11),

    notPatCopy (12),
    notPatOr   (13),
    notPatXor  (14),
    notPatBic  (15);

	private PatternMode(int id) {
		this.id = id;
	}

	private int id;

	public int id() {
		return id;
	}
}
