package it.unicam.coloni.hackhub.context.workspace.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadSummary {
        private long openTickets;
        private long scheduledMeetings;
        private long submittedReports;
}
