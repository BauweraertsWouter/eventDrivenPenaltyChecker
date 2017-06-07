package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

public class LicensePlateDetailDto {
    private final String plateId;
    private final String nationalNumber;
    private final int euroNorm;

    private LicensePlateDetailDto(String plateId, String nationalNumber, int euroNorm) {
        this.plateId = plateId;
        this.nationalNumber = nationalNumber;
        this.euroNorm = euroNorm;
    }

    public String getPlateId() { return plateId; }

    public String getNationalNumber() { return nationalNumber; }

    public int getEuroNorm() { return euroNorm; }

    public static class LicensePlateDetailDtoBuilder {
        private String plateId;
        private String nationalNumber;
        private int euroNorm;

        public LicensePlateDetailDtoBuilder withPlateId(String plateId) {
            this.plateId = plateId;
            return this;
        }

        public LicensePlateDetailDtoBuilder withNationalNumber(String nationalNumber) {
            this.nationalNumber = nationalNumber;
            return this;
        }

        public LicensePlateDetailDtoBuilder withEuroNorm(int euroNorm) {
            this.euroNorm = euroNorm;
            return this;
        }

        public LicensePlateDetailDto build() {
            return new LicensePlateDetailDto(plateId, nationalNumber, euroNorm);
        }
    }
}
