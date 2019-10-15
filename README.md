# Assignment1

A simple HTTP client application

To run:
1. Git clone this repository
2. Compile using Maven in IntelliJ
3. In a terminal, navigate to `Assignment1/target`
4. run the command `java -jar httpc.jar (get|post) [-v] (-h "k:v") [-d inline-data] [-f input_file_name] url`
   - -v: verbose
   - -h: header with the format "key:value"
   - -d: inline data/message body
   - -f: file name that includes message body (note only one of -d or -f can be used)
   - url: the url
5. run `java -jar help [get|post]` for help
    - adding `get` or `post` to the request outputs specific help options for either a get or post request respectibly
