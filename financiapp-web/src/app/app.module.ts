import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { KeycloakAngularModule, KeycloakEventType, KeycloakOptions, KeycloakService } from 'keycloak-angular';
import { environment } from 'src/environments/environment';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

function inicializarAgenteKeycloak(keycloakService: KeycloakService) {
    var configuracoesKeycloak: KeycloakOptions = {
        config: {
            url: environment.keycloak_auth_url,
            realm: environment.keycloak_realm,
            clientId: environment.keycloak_client_id,
        },
        initOptions: {
            onLoad: 'login-required',
            silentCheckSsoRedirectUri:
                window.location.origin + '/assets/silent-check-sso.html'
        }
    };

    keycloakService.keycloakEvents$.subscribe(evt => {
        switch (evt.type) {
            case KeycloakEventType.OnTokenExpired: {
                console.log('OnTokenExpired')
            } break;
            case KeycloakEventType.OnAuthError: {
                console.log('OnAuthError')
            } break;
            case KeycloakEventType.OnAuthLogout: {
                console.log('OnAuthLogout')
            } break;
            case KeycloakEventType.OnAuthRefreshError: {
                console.log('OnAuthRefreshError')
            } break;
            case KeycloakEventType.OnAuthRefreshSuccess: {
                console.log('OnAuthRefreshSuccess')
            } break;
            case KeycloakEventType.OnAuthSuccess: {
                console.log('OnAuthSuccess')
            } break;
            case KeycloakEventType.OnReady: {
                console.log('OnReady')
            } break;
        }
    });

    return () => keycloakService.init(configuracoesKeycloak);
}

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        KeycloakAngularModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [
        {
            provide: APP_INITIALIZER,
            useFactory: inicializarAgenteKeycloak,
            multi: true,
            deps: [KeycloakService]
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
