import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css'],
  imports: [CommonModule]
})
export class FilmsComponent implements OnInit {
  films: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getFilms();
  }

  getFilms(): void {
    this.http.get<any[]>(`${environment.apiUrl}/films`)
      .subscribe(data => {
        this.films = data;
      });
  }
}