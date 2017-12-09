package p5_Package;

/**
 *
 * @author Joshua Pollock
 */
public class TestClass
{

    public static void main(String[] args)
    {
        System.out.println("Stack: ");
        UtilityClass utilStack = new UtilityClass(6, 103);
        System.out.println("    Pop: " + utilStack.sPop());
        System.out.println("    Push: " + utilStack.sPush(0));
        System.out.println("    Push: " + utilStack.sPush(1));
        System.out.println("    Push: " + utilStack.sPush(2));
        System.out.println("    Push: " + utilStack.sPush(3));
        System.out.println("    Push: " + utilStack.sPush(4));
        System.out.println("    Push: " + utilStack.sPush(5));
        System.out.println("    Peek: " + utilStack.sPeek());
        System.out.println("    Push above capacity: " + utilStack.sPush(6));
        System.out.println("    Pop: " + utilStack.sPop());
        System.out.println("    Pop: " + utilStack.sPop());

        System.out.println("\nIterator:");
        UtilityClass util = new UtilityClass(5, 101);
        System.out.println("    Add: " + util.iAdd(0));
        System.out.println("    Add: " + util.iAdd(1));
        System.out.println("    Add: " + util.iAdd(2));
        System.out.println("    Add: " + util.iAdd(3));
        System.out.println("    Add: " + util.iAdd(4));
        System.out.println("    GaC: " + util.iGetAtCurrent());
        System.out.println("    Move Next: " + util.iMoveNext());
        System.out.println("    GaC: " + util.iGetAtCurrent());
        System.out.println("    Set to end: " + util.iSetToEnd());
        System.out.println("    GaC: " + util.iGetAtCurrent());
        System.out.println("    Set to begin: " + util.iSetToBeginning());
        System.out.println("    GaC: " + util.iGetAtCurrent());
        System.out.println("    Add above capacity: " + util.iAdd(4));

        System.out.println("\nQueue:");
        UtilityClass queueUtil = new UtilityClass(5, 102);


        System.out.println("    Enqueue: " + queueUtil.qEnqueue(0));
        System.out.println("    Enqueue: " + queueUtil.qEnqueue(1));
        System.out.println("    Enqueue: " + queueUtil.qEnqueue(2));
        System.out.println("    Dequeue (0): " + queueUtil.qDequeue());
        System.out.println("    Enqueue: " + queueUtil.qEnqueue(3));
        System.out.println("    Enqueue: " + queueUtil.qEnqueue(4));
        System.out.println("    Enqueue: " + queueUtil.qEnqueue(5));
        System.out.println("    Dequeue (0): " + queueUtil.qDequeue());



    }

}
