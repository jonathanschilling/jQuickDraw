package de.labathome.jQuickDraw;

public enum TransferMode {
    blend      (32),
    addPin     (33),
    addOver    (34),
    subPin     (35),
    transparent(36),
    addMax     (37),
    subOver    (38),
    adMin      (39);

	private TransferMode(int id) {
		this.id = id;
	}

	private int id;

	public int id() {
		return id;
	}

}
