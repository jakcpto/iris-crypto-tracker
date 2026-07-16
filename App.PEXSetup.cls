Class App.PEXSetup
{

Parameter LanguageServer = "%Java Server";

ClassMethod Setup() As %Status
{
    Set sc = ..RegisterComponents()
    Quit sc
}

ClassMethod ConfigureAtBuild() As %Status
{
    Quit ..ConfigureLanguageServer()
}

ClassMethod ConfigureLanguageServer() As %Status
{
    If '$system.external.serverExists(..#LanguageServer) {
        Quit ##class(%SYSTEM.Status).Error(5001, "App.PEXSetup", "ConfigureLanguageServer", "%Java Server definition is missing")
    }

    Set mod = $system.external.getServer(..#LanguageServer)
    Set mod.BindToIPAddress = "jgw"
    Set mod.Port = 55555
    Set mod.ClassPath = "/jgw/CryptoService.jar:/jgw/CryptoOperations.jar"
    Set srv = $system.external.modifyServer(mod)
    If srv="" {
        Quit ##class(%SYSTEM.Status).Error(5001, "App.PEXSetup", "ConfigureLanguageServer", "Failed to configure %Java Server for remote jgw")
    }

    Quit ##class(%SYSTEM.Status).OK()
}

ClassMethod RegisterComponents() As %Status
{
    Set sc = ##class(EnsLib.PEX.Utils).RegisterComponent("Crypto.service.CryptoService", ..#LanguageServer, "/jgw/CryptoService.jar", 1)
    If $System.Status.IsError(sc) Quit sc

    Set sc = ##class(EnsLib.PEX.Utils).RegisterComponent("Crypto.operation.TelegramBotOperation", ..#LanguageServer, "/jgw/CryptoOperations.jar", 1)
    If $System.Status.IsError(sc) Quit sc

    Set sc = ##class(EnsLib.PEX.Utils).RegisterComponent("Crypto.operation.CryptoMailOperation", ..#LanguageServer, "/jgw/CryptoOperations.jar", 1)
    If $System.Status.IsError(sc) Quit sc

    Set sc = ##class(EnsLib.PEX.Utils).RegisterComponent("Crypto.operation.CryptoCsvOperation", ..#LanguageServer, "/jgw/CryptoOperations.jar", 1)
    Quit sc
}

}
