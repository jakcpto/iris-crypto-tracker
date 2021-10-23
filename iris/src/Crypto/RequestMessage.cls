Class Crypto.RequestMessage Extends Ens.Request
{

Property ToEmailAddress As %String;

Property Request As Crypto.Request;

Storage Default
{
<Data name="RequestMessageDefaultData">
<Subscript>"RequestMessage"</Subscript>

<Value name="1">
<Value>ToEmailAddress</Value>
</Value>

<Value name="4">
<Value>Request</Value>
</Value>

</Data>
<DefaultData>RequestMessageDefaultData</DefaultData>
<Type>%Storage.Persistent</Type>
}

}
