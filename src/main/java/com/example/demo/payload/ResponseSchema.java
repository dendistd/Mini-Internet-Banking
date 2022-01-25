package com.example.demo.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSchema<T> {
	@JsonProperty("error_schema")
	private ErrorSchema errorSchema;
	
	@JsonProperty("output_schema")
	@JsonInclude(Include.NON_NULL)
	private T outputSchema;

	public ResponseSchema( ) {
	}
	
	public ResponseSchema(ErrorSchema errorSchema) {
		this.errorSchema = errorSchema;
		
	}

	public ErrorSchema getErrorSchema() {
		return errorSchema;
	}

	public void setErrorSchema(ErrorSchema errorSchema) {
		this.errorSchema = errorSchema;
	}

	public T getOutputSchema() {
		return outputSchema;
	}

	public void setOutputSchema(T outputSchema) {
		this.outputSchema = outputSchema;
	}

	@Override
	public String toString() {
		return "ResponseSchema [errorSchema=" + errorSchema + ", outputSchema=" + outputSchema + "]";
	}
	
	

}
