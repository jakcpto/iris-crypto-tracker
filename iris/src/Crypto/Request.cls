Class Crypto.Request Extends (%Persistent, %JSON.Adaptor, %XML.Adaptor)
{

Parameter %JSONIGNOREINVALIDFIELD = 1;

Property OriginalJSON As %String(%JSONINCLUDE = "none", MAXLEN = 3000000);

Property PercentChange30m As %Float;

Property PercentChange1h As %Float;

Property PercentChange24h As %Float;

Property PercentChange30d As %Float;

Property Price As %Float;

Property LastUpdate As %String;

Storage Default
{
<Data name="RequestDefaultData">
<Value name="1">
<Value>%%CLASSNAME</Value>
</Value>
<Value name="2">
<Value>OriginalJSON</Value>
</Value>

<Value name="3">
<Value>PercentChange30m</Value>
</Value>

<Value name="3">
<Value>PercentChange1h</Value>
</Value>

<Value name="3">
<Value>PercentChange24h</Value>
</Value>

<Value name="3">
<Value>PercentChange30d</Value>
</Value>

<Value name="3">
<Value>Price</Value>
</Value>

<Value name="3">
<Value>LastUpdate</Value>
</Value>

</Data>
<Type>%Storage.Persistent</Type>
}

}
