import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'financiapp-web';
    id = '';

    constructor(private keycloakService: KeycloakService, 
        private httpClient:HttpClient){}

    logout(): void {
        this.keycloakService.logout();
    }

    testeCreate(): void {
        this.httpClient.post<{id:string}>('http://localhost:4000/api/v1/createFinancialPlan', { }).subscribe(r => {
            this.id = r['id'];
        })
    }

    testeUpdate(): void {
        this.httpClient.put('http://localhost:4000/api/v1/updateFinancialPlan', { id: this.id }).subscribe(r => {
            alert('foi');
        })
    }

}
