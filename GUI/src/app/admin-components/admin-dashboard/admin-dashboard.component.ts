import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  faBars,
  faCaretDown,
  faUsers,
  faUserTie,
  faUsersCog,
  faTasks,
  faArrowRight,
  faUserCircle,
  faComment,
  faPhone,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
})
export class AdminDashboardComponent implements OnInit {
  constructor(private http: HttpClient) {}
  navToggle = faBars;
  dropdownToggle = faCaretDown;
  usersIcon = faUsers;
  clientsIcon = faUserTie;
  deptIcon = faUsersCog;
  projectIcon = faTasks;
  arrowRightIcon = faArrowRight;
  userCircle = faUserCircle;
  commentIcon = faComment;
  phoneIcon = faPhone;

  adminData = {
    countsData: {
      employeeCount: '40',

      clientCount: '34',

      departmentCount: '4',

      projectsCount: '7',
    },

    projectsData: [
      {
        projectName: 'Equifax',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
      {
        projectName: 'I9',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
      {
        projectName: 'lOQBOX',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
      {
        projectName: 'mMS',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
      {
        projectName: 'lOQBOX',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
      {
        projectName: 'lOQBOX',
        projectClient: 'venkat',
        empCount: '30',
        projectDeadline: '20-02-2022',
      },
    ],
  };
  ngOnInit(): void {
    // console.log('hiii', this.fetchPosts());
    // this.fetchPosts();
  }
  // onGetProjects() {
  //   return this.http.get<any>(
  //     'https://authapp-ba96e-default-rtdb.firebaseio.com/projectsData.json'
  //   );
  // }
  // fetchPosts() {
  //   return this.http
  //     .get<any>(
  //       'https://authapp-ba96e-default-rtdb.firebaseio.com/projectsData'
  //     )
  //     .pipe(
  //       map((responseData) => {
  //         console.log('hi', responseData);
  //         const postsArray: projectsData[] = [];
  //         for (const key in responseData) {
  //           if (responseData.hasOwnProperty(key)) {
  //             postsArray.push({ ...responseData[key], id: key });
  //           }
  //         }
  //         return postsArray;
  //       })
  //     );
  // }
}
