package gcfv2;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.IOException;

import okhttp3.*;

public class TranslateMailHttpFunction implements HttpFunction {
	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {

		final OkHttpClient httpClient = new OkHttpClient();

		// Set response content type to JSON
		response.setContentType("application/json");

		// Parse the JSON request body
		JsonObject jsonRequest = JsonParser.parseString(request.getReader().lines().reduce("", String::concat))
				.getAsJsonObject();

		// Check if the 'input' field is present
		if (!jsonRequest.has("input")) {
			JsonObject errorResponse = new JsonObject();
			errorResponse.addProperty("error", "'input' field is required");
			BufferedWriter writer = response.getWriter();
			writer.write(errorResponse.toString());
			response.setStatusCode(400);
			return;
		}

		// Build the request to the external URL
		String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyD0Gk7H9vNKDKmmqT0RLkCwoTpN2SmpZGQ";

		String geminiPayLoad =

				String.format("{\n" + "  \"contents\": [\n" + "    {\n" + "      \"parts\": [\n" + "        {\n"
						+ "          \"text\": \"%s\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}",
						jsonRequest.get("input").getAsString());

		RequestBody body = RequestBody.create(geminiPayLoad, MediaType.parse("application/json"));
		Request externalRequest = new Request.Builder().url(url).post(body)
				.addHeader("Content-Type", "application/json").build();

		// Send the request and handle the response
		try (Response externalResponse = httpClient.newCall(externalRequest).execute()) {
			if (!externalResponse.isSuccessful()) {
				throw new IOException("Unexpected code " + externalResponse);
			}
			JsonObject jsonResponse = JsonParser.parseString(externalResponse.body().string()).getAsJsonObject();
			BufferedWriter writer = response.getWriter();
			package gcfv2;

			import com.google.cloud.functions.HttpFunction;
			import com.google.cloud.functions.HttpRequest;
			import com.google.cloud.functions.HttpResponse;
			import com.google.gson.JsonObject;
			import com.google.gson.JsonParser;
			import java.io.BufferedWriter;
			import java.io.IOException;

			import okhttp3.*;

			public class TranslateMailHttpFunction implements HttpFunction {
				@Override
				public void service(HttpRequest request, HttpResponse response) throws IOException {

					final OkHttpClient httpClient = new OkHttpClient();

					// Set response content type to JSON
					response.setContentType("application/json");

					// Parse the JSON request body
					JsonObject jsonRequest = JsonParser.parseString(request.getReader().lines().reduce("", String::concat))
							.getAsJsonObject();

					// Check if the 'input' field is present
					if (!jsonRequest.has("input")) {
						JsonObject errorResponse = new JsonObject();
						errorResponse.addProperty("error", "'input' field is required");
						BufferedWriter writer = response.getWriter();
						writer.write(errorResponse.toString());
						response.setStatusCode(400);
						return;
					}

					// Build the request to the external URL
					String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyD0Gk7H9vNKDKmmqT0RLkCwoTpN2SmpZGQ";

					String geminiPayLoad =

							String.format("{\n" + "  \"contents\": [\n" + "    {\n" + "      \"parts\": [\n" + "        {\n"
									+ "          \"text\": \"%s\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}",
									jsonRequest.get("input").getAsString());

					RequestBody body = RequestBody.create(geminiPayLoad, MediaType.parse("application/json"));
					Request externalRequest = new Request.Builder().url(url).post(body)
							.addHeader("Content-Type", "application/json").build();

					// Send the request and handle the response
					try (Response externalResponse = httpClient.newCall(externalRequest).execute()) {
						if (!externalResponse.isSuccessful()) {
							throw new IOException("Unexpected code " + externalResponse);
						}
						JsonObject jsonResponse = JsonParser.parseString(externalResponse.body().string()).getAsJsonObject();
						BufferedWriter writer = response.getWriter();
						writer.write(jsonResponse.toString());
					} catch (Exception e) {
						JsonObject errorResponse = new JsonObject();
						errorResponse.addProperty("error", "Error processing the request: " + e.getMessage());
						BufferedWriter writer = response.getWriter();
						writer.write(errorResponse.toString());
						response.setStatusCode(500);
					}
				}
			}

			writer.write(jsonResponse.toString());
		} catch (Exception e) {
			JsonObject errorResponse = new JsonObject();
			errorResponse.addProperty("error", "Error processing the request: " + e.getMessage());
			BufferedWriter writer = response.getWriter();
			writer.write(errorResponse.toString());
			response.setStatusCode(500);
		}
	}
}
