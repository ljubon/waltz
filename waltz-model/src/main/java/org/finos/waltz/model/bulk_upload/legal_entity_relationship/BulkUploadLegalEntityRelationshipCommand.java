package org.finos.waltz.model.bulk_upload.legal_entity_relationship;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.finos.waltz.model.bulk_upload.BulkUpdateMode;
import org.finos.waltz.model.command.Command;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableBulkUploadLegalEntityRelationshipCommand.class)
@JsonDeserialize(as = ImmutableBulkUploadLegalEntityRelationshipCommand.class)
public abstract class BulkUploadLegalEntityRelationshipCommand implements Command {

    public abstract long legalEntityRelationshipKindId();

    @Value.Redacted
    public abstract String inputString();

    @Value.Default
    public BulkUpdateMode updateMode() {
        return BulkUpdateMode.ADD_ONLY;
    }

    ;

}
