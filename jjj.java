public class Send
{
  // Set these up before calling send/receive.
  public Writer out;
  public BufferedReader in;
  
  public void send(Message m)
  {
    String s = m.from + ";" + m.to + ";" + m.ident + ";" + m.signature + ";" +
      m.payload + ";" + m.footer;
    try {
      if (s.length() % 3 == 0) {
        out.write(Encoder.encode(s, EncodingType.ALPHA) + "\n");
      }
      else if (s.length() % 3 == 1) {
        out.write(Encoder.encode(s, EncodingType.BETA) + "\n");
      }
      else if (s.length() % 3 == 2) {
        out.write(Encoder.encode(s, EncodingType.GAMMA) + "\n");
      }
    }
    catch (EncodingException | IOException e) {
      e.printStackTrace();
    }
  }

  public Message receive()
  {
    Message m = new Message();
    try {
      String s = in.readLine();
      String s2 = null;
      if (s.length() % 3 == 0) {
        s2 = Encoder.decode(s, EncodingType.ALPHA);
      }
      else if (s.length() % 3 == 1) {
        s2 = Encoder.decode(s, EncodingType.BETA);
      }
      else if (s.length() % 3 == 2) {
        s2 = Encoder.decode(s, EncodingType.GAMMA);
      }
      String[] parts = s2.split(";");
      m.from = parts[0];
      m.to = parts[1];
      m.ident = Integer.parseInt(parts[2]);
      m.signature = parts[3];
      m.payload = parts[4];
      m.footer = parts[5];
    }
    catch (EncodingException | IOException e) {
      e.printStackTrace();
    }
    return m;
  }
}
