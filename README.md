# quotes-service
Simple service to generate random quotes for experiments. For quote generation it uses https://github.com/DiUS/java-faker.

API consists of the following endpoints:
- /, allows GET method and returns a single quote as a text. This endpoint is synchronous.
- /api/v2/quotes/ , allows GET and returns a JSON with array of structures that contains quotes. Reactive implementation.
- /api/v2/quotes/{id}, allows GET and returns a JSON structure that contains one quote. Reactive implementation.
