import { TestBed } from '@angular/core/testing';

import { ProfileRequesterService } from './profile-requester.service';

describe('ProfileRequesterService', () => {
  let service: ProfileRequesterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileRequesterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
