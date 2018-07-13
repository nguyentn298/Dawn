package com.dante.learn.pattern.creation.builder;

public class PizzaWithBuilderPattern {
	private int size;
	private int cheese;
	private int pepperoni;
	private int bacon;

	private PizzaWithBuilderPattern(Builder builder) {
		size = builder.size;
		cheese = builder.cheese;
		pepperoni = builder.pepperoni;
		bacon = builder.bacon;
	}
	
	@Override
	public String toString() {
		return String.format("cheese[%s] - pepperoni[%s] - bacon[%s]", cheese, pepperoni, bacon);
	}
	
	public static class Builder {
		// required
		private final int size;

		// optional
		private int cheese = 0;
		private int pepperoni = 0;
		private int bacon = 0;

		public Builder(int size) {
			this.size = size;
		}

		public Builder cheese(int value) {
			cheese = value;
			return this;
		}

		public Builder pepperoni(int value) {
			pepperoni = value;
			return this;
		}

		public Builder bacon(int value) {
			bacon = value;
			return this;
		}

		public PizzaWithBuilderPattern build() {
			return new PizzaWithBuilderPattern(this);
		}
	}
}
