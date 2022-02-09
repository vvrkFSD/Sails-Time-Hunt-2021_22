import { Component, OnInit } from '@angular/core';
import {
  faUsers,
  faEdit,
  faInfoCircle,
  faTasks,
  faSearch,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-projects-component',
  templateUrl: './projects-component.component.html',
  styleUrls: ['./projects-component.component.scss'],
})
export class ProjectsComponentComponent implements OnInit {
  userIcon = faUsers;
  editIcon = faEdit;
  viewIcon = faInfoCircle;
  taskIcon = faTasks;
  searchIcon = faSearch;
  constructor() {}

  projects = [
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Inactive',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
    {
      status: 'Active',
      projectName: 'Equifax',
      image:
        'https://upload.wikimedia.org/wikipedia/commons/4/48/Outdoors-man-portrait_%28cropped%29.jpg',
      clientName: 'venkat2',
      employeeCount: '44',
    },
  ];
  ngOnInit(): void {}
}
