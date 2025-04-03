import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { CommonModule } from '@angular/common';

// Declarar la interfaz fuera de la clase del componente
export interface FilmShortDTO {
  id: number;
  titulo: string;
}

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css'],
  imports: [CommonModule]
})
export class FilmsComponent implements OnInit {
  films: FilmShortDTO[] = []; // Cambiado a FilmShortDTO[]

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getFilms();
  }

  getFilms(): void {
    this.http.get<FilmShortDTO[]>(`${environment.apiUrl}films`)
      .subscribe(data => {
        this.films = data;
        console.log(this.films); // Para verificar la respuesta
      }, error => {
        console.error("Error al obtener las películas: ", error);
      });
  }
}