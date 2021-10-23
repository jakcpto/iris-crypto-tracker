Class Crypto.RequestRoutingRule Extends Ens.Rule.Definition
{

Parameter RuleAssistClass = "EnsLib.MsgRouter.RuleAssist";

XData RuleDefinition [ XMLNamespace = "http://www.intersystems.com/rule" ]
{
<ruleDefinition alias="" context="EnsLib.MsgRouter.RoutingEngine" production="Crypto.CryptoProduction">
<ruleSet name="" effectiveBegin="" effectiveEnd="">
<rule name="">
<constraint name="msgClass" value="Crypto.RequestMessage"></constraint>
</rule>
<assign property="Document.ToEmailAddress" value="&quot;youremail@index.com&quot;"></assign>
<send transform="" target="TelegramBotOperation"></send>
<send transform="" target="MailOperation"></send>
<return></return>
</ruleSet>
</ruleDefinition>
}

}
