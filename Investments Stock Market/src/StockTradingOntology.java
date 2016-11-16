import jade.content.onto.*;
import jade.content.schema.*;

public class StockTradingOntology extends Ontology {
  // The name identifying this ontology
  public static final String ONTOLOGY_NAME = "Stock_Trading_Ontology";

  // VOCABULARY - 1 Concept(Stock) + 1 Predicate(Transaction_Costs) + 2 agents action(Agente Licitador e Agente Vendedor)

  // CONCEPT
  public static final String  STOCK                      = "Stock";
  public static final String  STOCK_EXCHANGE_NAME        = "stock_exchange_name";
  public static final String  STOCK_COMPANY_NAME         = "stock_company_name";
  public static final String  STOCK_COMPANY_INDUSTRY     = "stock_company_industry";
  public static final String  STOCK_COMPANY_OWNERS       = "stock_company_owners";
  public static final String STOCK_AVAILABLE            = ""+0;
  public static final String   STOCK_LAST_STOCK_PRICE     = ""+0;
  public static final String STOCK_YEAR                 = ""+2016;
  public static final String STOCK_STOCK                = ""+0; // TODO Ver o que é esta Variável em Empresa.java
  public static final String STOCK_COMPANY_CAPITAL      = ""+0;

  // PREDICATE
  public static final String  TRANSACTION_COSTS          = "transaction_costs";
  public static final String  TRANSACTION_COSTS_ITEM     = "transaction_item";
  public static final String   TRANSACTION_COSTS_PRICE    = ""+0;
  public static final String TRANSACTION_COSTS_QUANTITY = ""+0;

  // AGENT ACTION - AGENTE LICITADOR
  public static final String  BUY                        = "buy";
  public static final String  BUY_ITEM                   = "buy_item";
  public static final String BUY_QUANTITY               = ""+0;

  // AGENT ACTION - AGENTE VENDEDOR
  public static final String  SELL                       = "sell";
  public static final String  SELL_ITEM                  = "sell_item";
  public static final String SELL_QUANTITY              = ""+0;


  // The singleton instance of this ontology
  private static Ontology theInstance = new StockTradingOntology();

  // Retrieve the singleton StockTradingOntology
  public static Ontology getInstance() {
    return theInstance;
  }

  // Private constructor
  private StockTradingOntology() {
    // The StockTradingOntology ontology extends the BasicOntology
    super(ONTOLOGY_NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());
    try {
      // Both Concept, Predicate and agents action need their own java classes

      // Concept Stock is represented by Empresa.java
      add(new ConceptSchema(STOCK), Empresa.class);
      // Predicate Transaction_Costs is represented by Transaction_Costs.java
      add(new PredicateSchema(TRANSACTION_COSTS), Transaction_Costs.class);
      // agent action Buy is represented by BidderAgent.java
      add(new AgentActionSchema(BUY), BidderAgent.class);
      // agent action Sell is represented by SellerAgent.java
      add(new AgentActionSchema(SELL), SellerAgent.class);

      // Structure of the schema for the Stock concept
      ConceptSchema cs = (ConceptSchema) getSchema(STOCK);
      // Adding VOCABULARY to ConceptSchema
      cs.add(STOCK_EXCHANGE_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      cs.add(STOCK_COMPANY_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      cs.add(STOCK_COMPANY_INDUSTRY, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      // Could be Optional? If so add like this,
      // cs.add(STOCK_COMPANY_OWNERS, (PrimitiveSchema) getSchema(BasicOntology.STRING), 0, ObjectSchema.OPTIONAL);
      cs.add(STOCK_COMPANY_OWNERS, (PrimitiveSchema) getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
      cs.add(STOCK_AVAILABLE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      cs.add(STOCK_LAST_STOCK_PRICE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      cs.add(STOCK_YEAR, (PrimitiveSchema) getSchema(BasicOntology.STRING));
      cs.add(STOCK_STOCK, (PrimitiveSchema) getSchema(BasicOntology.STRING)); // TODO Ver o que é esta Variável em Empresa.java
      cs.add(STOCK_COMPANY_CAPITAL, (PrimitiveSchema) getSchema(BasicOntology.STRING));

      // Structure of the schema for the Costs predicate
      PredicateSchema ps = (PredicateSchema) getSchema(TRANSACTION_COSTS);
      ps.add(TRANSACTION_COSTS_ITEM, getSchema(BasicOntology.STRING));
      ps.add(TRANSACTION_COSTS_PRICE, getSchema(BasicOntology.STRING));
      ps.add(TRANSACTION_COSTS_QUANTITY, getSchema(BasicOntology.STRING));

      // Structure of the schema for the BidderAgent agent action
      AgentActionSchema asBidder = (AgentActionSchema) getSchema(BUY);
      asBidder.add(BUY_ITEM, (ConceptSchema) getSchema(STOCK));
      asBidder.add(BUY_QUANTITY, (ConceptSchema) getSchema(STOCK));

      // Structure of the schema for the SellerAgent agent action
      AgentActionSchema asSeller = (AgentActionSchema) getSchema(SELL);
      asSeller.add(SELL_ITEM, (ConceptSchema) getSchema(STOCK));
      asSeller.add(BUY_QUANTITY, (ConceptSchema) getSchema(STOCK));
    }
    catch (OntologyException oe) {
        oe.printStackTrace();
    }
  }
}
