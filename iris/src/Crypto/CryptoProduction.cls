Class Crypto.CryptoProduction Extends Ens.Production
{

XData ProductionDefinition
{
<Production Name="Crypto.CryptoProduction" LogGeneralTraceEvents="false">
  <Description></Description>
  <ActorPoolSize>2</ActorPoolSize>
  <Item Name="CryptoService" Category="" ClassName="EnsLib.PEX.BusinessService" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayName">%Java Server</Setting>
    <Setting Target="Host" Name="%remoteClassname">Crypto.service.CryptoService</Setting>
    <Setting Target="Adapter" Name="CallInterval">60</Setting>
  </Item>
  <Item Name="RequestRoutingRule" Category="" ClassName="EnsLib.MsgRouter.RoutingEngine" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="false" Schedule="">
    <Setting Target="Host" Name="BusinessRuleName">Crypto.RequestRoutingRule</Setting>
  </Item>
  <Item Name="TelegramBotOperation" Category="" ClassName="EnsLib.PEX.BusinessOperation" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayName">%Java Server</Setting>
    <Setting Target="Host" Name="%remoteClassname">Crypto.operation.TelegramBotOperation</Setting>
  </Item>
  <Item Name="MailOperation" Category="" ClassName="EnsLib.PEX.BusinessOperation" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayName">%Java Server</Setting>
    <Setting Target="Host" Name="%remoteClassname">Crypto.operation.CryptoMailOperation</Setting>
  </Item>
  <Item Name="CsvOperation" Category="" ClassName="EnsLib.PEX.BusinessOperation" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayName">%Java Server</Setting>
    <Setting Target="Host" Name="%remoteClassname">Crypto.operation.CryptoCsvOperation</Setting>
  </Item>
</Production>
}

}
