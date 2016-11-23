public classe EnsSeance {

    private int numEns;
    private ArrayList seanceEns = new ArrayList();

    public EnsSeance (int numEns, ArrayList seanceEns) {
        this.numEns = numEns;
        this.seanceEns = seanceEns;
    }

    public void setNumEns (int numEns){
        this.numEns = numEns;
    }

    public void setSeanceEns (ArrayList seanceEns){
        this.seanceEns = seanceEns;
    }

}