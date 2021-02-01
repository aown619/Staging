import { TestBed } from '@angular/core/testing';

import { InviteStatusService } from './inviteStatus.service';

describe('InviteStatusService', () => {
  let service: InviteStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InviteStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
