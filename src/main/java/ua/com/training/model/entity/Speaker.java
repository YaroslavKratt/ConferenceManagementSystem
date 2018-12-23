package ua.com.training.model.entity;

public class Speaker extends User{
    private double speakerBonus;
    private double speakerRating;

    private Speaker(Builder builder){
        super(builder);
        this.speakerBonus=builder.speakerBonus;
        this.speakerRating = builder.speakerRating;
    }
    public double getSpeakerBonus() {
        return speakerBonus;
    }

    public double getSpeakerRating() {
        return speakerRating;
    }
    public static class Builder extends User.Builder{
        private double speakerBonus;
        private double speakerRating;

        public Builder setSpeakerBonus(int speakerBonus) {
            this.speakerBonus = speakerBonus;
            return this;
        }
        public Builder speakerRating(int speakerRating){
            this.speakerRating = speakerRating;
            return this;
        }
        @Override
        public Speaker build(){
            return new Speaker(this);
        }
    }
}
