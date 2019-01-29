package nl.nils.console;
import java.util.ArrayList;


/**
 * A subclass that can be used to queue items up and print them at once
 */
    public class BufferedPrinter {
        private ArrayList<Object> buffer;
        private final Console console;

        /**
         * Initializes the bufferedPrinter
         * @param console The console in which the buffered printer prints
         */
        public BufferedPrinter(Console console) {
            assert console!=null:"console is null";
            buffer = new ArrayList<Object>();
            this.console=console;
        }

        /**
         * Adds an object to the buffer
         * 
         * @param o
         */
        public void add(Object o) {
            assert buffer != null;
            buffer.add(o);
        }

        /**
         * Prints all the items in the buffer
         */
        public void dump() {
            assert console!=null:"console is null";
            assert buffer != null;
            String print = "";
            while (buffer.size() > 0) {
                print = print + buffer.get(0).toString();
                buffer.remove(0);
            }
            console.print(print);
        }

        /**
         * Prints all items in the buffer seperated by new lines
         */
        public void lineDump() {
            assert console!=null:"console is null";
            assert buffer != null;
            String print = "";
            while (buffer.size() > 0) {
                print = print + buffer.get(0).toString() + "\n";
                buffer.remove(0);
            }
            console.linePrint(print);
        }

        /**
         * clears the buffer
         */
        public void clear() {
            assert true;
            buffer = new ArrayList<Object>();
        }

    }